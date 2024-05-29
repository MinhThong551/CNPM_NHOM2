package com.chatapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.daos.impl.FriendDao;
import com.chatapp.models.Friend;
import com.chatapp.models.User;
import com.chatapp.services.UserServiceInterface;
import com.chatapp.services.impl.UserService;

@WebServlet("/chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Tạo một instance của UserServiceInterface, được triển khai bởi UserService
	private UserServiceInterface userService = UserService.getInstance();
	// Tạo một instance của FriendDao để thực hiện các thao tác cơ sở dữ liệu liên quan đến bạn bè
	private FriendDao friendDao = new FriendDao();
	
	public ChatController() {
		super();
	}

	// Xử lý các yêu cầu GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy người dùng hiện tại từ session
		User currentUser = (User) request.getSession().getAttribute("user");
		// Tìm danh sách bạn bè của người dùng hiện tại
		List<User> friends = userService.findFriends(currentUser.getUsername());

		// Đặt danh sách bạn bè và người dùng hiện tại làm thuộc tính của request
		request.setAttribute("friends", friends);
		request.setAttribute("user", currentUser);

		// Chuyển tiếp request đến trang JSP chatbox để hiển thị
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/chatbox.jsp");
		rd.forward(request, response);
	}
	
	// Xử lý các yêu cầu POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy các tham số sender, receiver, status và isAccept từ request
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		boolean isAccept = Boolean.parseBoolean(request.getParameter("isAccept"));
		
		// Lưu quan hệ bạn bè vào cơ sở dữ liệu
		friendDao.saveFriend(isAccept, new Friend(sender, receiver, sender, status));

		// Chuyển hướng trở lại trang chat
		response.sendRedirect("/chat");
	}
}
