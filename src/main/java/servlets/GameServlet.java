package servlets;

import dao.GameDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String choice = request.getParameter("choice");
        if (choice != null) {
            // Здесь идет логика игры "Камень-Ножницы-Бумага"
            String[] choices = { "rock", "paper", "scissors" };
            Random random = new Random();
            String computerChoice = choices[random.nextInt(choices.length)];
            String result = determineWinner(choice, computerChoice);

            // Сохраняем результат игры в базе данных
            GameDAO gameDAO = new GameDAO("rock_paper_scissors_db", "root", "adminadmin");
            gameDAO.saveGameResult(userId, result);

            request.setAttribute("userChoice", choice);
            request.setAttribute("computerChoice", computerChoice);
            request.setAttribute("result", result);

            request.getRequestDispatcher("/game.jsp").forward(request, response);
        } else {
            response.sendRedirect(".");
        }
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "draw";
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors"))
                || (userChoice.equals("paper") && computerChoice.equals("rock"))
                || (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "win";
        } else {
            return "lose";
        }
    }
}