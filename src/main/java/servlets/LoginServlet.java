package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username != null && !username.trim().isEmpty()) {
            // Здесь происходит аутентификация пользователя (предположим, userId - это идентификатор пользователя после аутентификации)
            int userId = authenticateUserAndGetUserId(username);

            if (userId > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username);
                response.sendRedirect("game");
            } else {
                // Неверные учетные данные пользователя
                response.sendRedirect(".");
            }
        } else {
            // Пользователь не указал имя пользователя
            response.sendRedirect(".");
        }
    }

    // Ваш код аутентификации пользователя.
    // Возвращает идентификатор пользователя (userId) в случае успешной аутентификации.
    private int authenticateUserAndGetUserId(String username) {
        // Здесь может быть проверка учетных данных из базы данных, файлов, внешних сервисов и т. д.
        // В данном примере просто возвращаем фиктивный идентификатор.
        // В реальной реализации это должно быть ассоциировано с базой данных или другим источником данных.
        return 1;
    }
}