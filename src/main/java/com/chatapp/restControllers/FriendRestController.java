package com.chatapp.restControllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.daos.impl.FriendDao;
import com.chatapp.models.Friend;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/friend-rest-controller")
public class FriendRestController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FriendRestController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin người gửi và người nhận từ request
        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");

        // Tìm kiếm thông tin về bạn bè trong cơ sở dữ liệu
        Friend friend = new FriendDao().findFriend(sender, receiver);
        
        // Nếu không tìm thấy bạn bè, tạo một đối tượng Friend mặc định
        if (friend == null) {
            friend = new Friend("any", "any", "any", false);
        }

        // Chuyển đổi đối tượng Friend thành định dạng JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(friend);

        // Thiết lập loại và mã hóa ký tự cho phản hồi HTTP
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Gửi dữ liệu JSON trả về cho client
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
