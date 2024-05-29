package com.chatapp.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

public abstract class FileServiceAbstract {

    // Đường dẫn gốc cho lưu trữ file
    public static Path rootLocation = Paths.get("archive");

    // URL gốc cho truy cập file
    public static String rootURL = "";

    /**
     * Chuyển đổi loại file thành thẻ HTML tương ứng để hiển thị trên giao diện người dùng.
     * 
     * @param type     Loại file
     * @param username Tên người dùng
     * @param message  Tên file hoặc nội dung file
     * @return Thẻ HTML tương ứng với loại file
     */
    public static String toTagHtml(String type, String username, String message) {
        String tag = "";
        String url = rootURL + username + "/" + message;
        if (type.startsWith("audio")) {
            // Nếu là audio
            tag = "<audio controls>\r\n" + "  <source src=\"" + url + "\" type=\"" + type + "\">\r\n" + "</audio>";
        } else if (type.startsWith("video")) {
            // Nếu là video
            tag = "<video width=\"400\" controls>\r\n" + "  <source src=\"" + url + "\" type=\"" + type + "\">\r\n"
                    + "</video>";
        } else if (type.startsWith("image")) {
            // Nếu là hình ảnh
            tag = "<img src=\"" + url + "\" alt=\"\">";
        } else {
            // Nếu là file khác
            tag = "<a href=" + url + ">" + message + "</a>";
        }
        return tag;
    }

    // Kích thước buffer mặc định cho việc gửi file
    protected static final int DEFAULT_BUFFER_SIZE = 10240;

    /**
     * Phương thức trừu tượng để xử lý việc gửi file đến client.
     * 
     * @param file         File cần gửi
     * @param contentType Loại nội dung của file
     * @param response     HttpServletResponse
     */
    public abstract void handleStreamFileToClient(File file, String contentType, HttpServletResponse response);
}
