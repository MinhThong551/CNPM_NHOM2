package com.chatapp.daos;

import java.util.List;

import com.chatapp.models.Conversation;
import com.chatapp.models.User;

public interface ConversationDaoInterface {
	// Lưu một cuộc trò chuyện và danh sách người dùng tham gia
	void saveConversation(Conversation conversation, List<User> users);

	// Tìm tất cả các cuộc trò chuyện của một người dùng theo tên người dùng
	List<Conversation> findAllConversationsByUsername(String username);

	// Tìm một cuộc trò chuyện theo ID
	Conversation findConversationById(Long id);
	
	// Tìm các cuộc trò chuyện của người dùng theo từ khóa
	List<Conversation> findConversationsOfUserByKeyword(String username, String keyword);

	// Xóa một cuộc trò chuyện theo ID
	void deleteConversationById(Long id);

	// Xóa một người dùng khỏi cuộc trò chuyện
	void deleteUserFromConversation(Long conversationId, String username);
}
