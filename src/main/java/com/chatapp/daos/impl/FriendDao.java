package com.chatapp.daos.impl;

import java.util.List;

import com.chatapp.mappers.impl.FriendMapper;
import com.chatapp.models.Friend;

public class FriendDao extends GenericDao<Friend> {

	// Lưu thông tin bạn bè, chấp nhận hoặc gửi yêu cầu kết bạn
	public void saveFriend(boolean isAccept, Friend friend) {
		String sender = friend.getSender();
		String receiver = friend.getReceiver();
		String owner = friend.getOwner();
		Boolean status = friend.isStatus();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		if (isAccept) {
			// Cập nhật trạng thái kết bạn nếu yêu cầu được chấp nhận
			sql1.append("update friends set status=? where sender = ? and receiver = ?");
			sql2.append("update friends set status=? where sender = ? and receiver = ?");
			save(sql1.toString(), status, sender, receiver);
			save(sql2.toString(), status, receiver, sender);
		} else {
			// Kiểm tra xem mối quan hệ bạn bè đã tồn tại chưa
			StringBuilder sqlCheckExist = new StringBuilder();
			sqlCheckExist.append("select * from friends");
			sqlCheckExist.append(" where sender = ? and receiver = ?");
			List<Friend> friends = query(sqlCheckExist.toString(), new FriendMapper(), sender, receiver);
			if (friends.isEmpty()) {
				// Chèn mới quan hệ bạn bè nếu chưa tồn tại
				sql1.append("insert into friends values(?,?,?,?)");
				sql2.append("insert into friends values(?,?,?,?)");

				save(sql1.toString(), sender, receiver, owner, status);
				save(sql2.toString(), receiver, sender, owner, status);
			}
		}
	}

	// Tìm bạn bè theo người gửi và người nhận
	public Friend findFriend(String sender, String receiver) {
		StringBuilder sql = new StringBuilder(
				"select sender,receiver, owner, status from friends where sender=? and receiver=?");

		List<Friend> friends = query(sql.toString(), new FriendMapper(), sender, receiver);
		return friends.isEmpty() ? null : friends.get(0);
	}
}
