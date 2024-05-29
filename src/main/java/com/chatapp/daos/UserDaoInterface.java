package com.chatapp.daos;

import java.util.List;

import com.chatapp.models.User;

public interface UserDaoInterface extends GenericDaoInterface<User> {
	// Tìm người dùng theo tên người dùng và mật khẩu
	User findByUserNameAndPassword(String userName, String password);

	// Lưu người dùng (đăng ký hoặc cập nhật thông tin)
	void saveUser(Boolean isRegister, User user);

	// Tìm bạn bè của người dùng theo tên người dùng
	List<User> findFriends(String userName);

	// Tìm bạn bè của người dùng theo từ khóa
	List<User> findFriendsByKeyWord(String userName, String keyword);

	// Tìm người dùng theo ID cuộc trò chuyện
	List<User> findUsersByConversationId(Long id);

	// Tìm bạn bè không có trong cuộc trò chuyện
	List<User> findFriendsNotInConversation(String userName, String keyword, Long conversationId);
}
