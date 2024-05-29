package com.chatapp.mappers.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chatapp.mappers.RowMapperInterface;
import com.chatapp.models.Friend;

public class FriendMapper implements RowMapperInterface<Friend> {

    @Override
    // Phương thức để ánh xạ một hàng của ResultSet thành một đối tượng Friend
    public Friend mapRow(ResultSet resultSet) {
        try {
            // Tạo một đối tượng Friend
            Friend friend = new Friend();
            // Thiết lập các thuộc tính của Friend từ các cột của ResultSet
            friend.setSender(resultSet.getString("sender").trim());
            friend.setReceiver(resultSet.getString("receiver").trim());
            friend.setOwner(resultSet.getString("owner").trim());
            friend.setStatus(resultSet.getBoolean("status"));
            return friend;
        } catch (SQLException e) {
            // Trả về null nếu có lỗi xảy ra trong quá trình ánh xạ
            return null;
        }
    }
}
