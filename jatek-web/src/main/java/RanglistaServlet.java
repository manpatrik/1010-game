import hu.alkfejl.dao.EredmenyDAO;
import hu.alkfejl.dao.EredmenyDAOinpl;
import hu.alkfejl.model.Eredmeny;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Ranglista")
public class RanglistaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        EredmenyDAO dao = new EredmenyDAOinpl();
        List<Eredmeny> rangsor = dao.findALl();
        PrintWriter out = response.getWriter();

        out.println(
                "<html>\n" +
                        "<head>" +
                            "<meta charset=\"UTF-8\">" +
                            "<title>Ranglista</title>" +
                            "<style>\n" +
                            "    table{" +
                            "        margin: 20px auto;" +
                            "        border-collapse: collapse;\n" +
                            "        border: black 4px solid;\n" +
                            "    }\n" +
                            "    td, th{" +
                            "        padding: 10px;" +
                            "        border: black 2px solid;\n" +
                            "    }\n" +
                            "</style>" +
                        "</head>\n" +
                        "<body>\n" +
                        "<center><h1>Ranglista</h1></center>" +
                        "<table>\n" +
                        "    <thead>\n" +
                        "        <tr>\n" +
                        "            <th>Pontszám</th>\n" +
                        "            <th>Név</th>\n" +
                        "            <th>Dátum</th>\n" +
                        "            <th>Idő</th>\n" +
                        "        </tr>\n" +
                        "    </thead>\n" +
                        "<tbody>");

        for (Eredmeny eredmeny : rangsor) {
            out.println("<tr>");
            out.println("<td>"+eredmeny.getScore()+"</td>");
            out.println("<td>"+eredmeny.getName()+"</td>");
            out.println("<td>"+eredmeny.getDate()+"</td>");
            out.println("<td>"+eredmeny.getTime()+"</td>");
            out.println("</tr>");
        }
        out.println("</tbody>" +
                "</table>" +
                "</body>" +
                "</html>");
    }
}
