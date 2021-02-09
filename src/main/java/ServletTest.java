import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//@javax.servlet.annotation.WebServlet(name = "ServletTest")
public class ServletTest extends HttpServlet {

    CrudService crudService=new CrudService();

    public ServletTest() throws SQLException {
    }

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        System.out.println("lalalalala");
//        super.init(config);
//    }

    @Override
    public void init() throws ServletException {
        System.out.println("lalalalala");
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id")==null){
            Student student=new Student();
            student.setName(request.getParameter("name"));
            student.setSurname(request.getParameter("surname"));
            crudService.post(student);
            response.sendRedirect("/");
//            doGet(request, response);
        } else {
            doPut(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter=response.getWriter();
        System.out.println(request.getParameter("name"));
        System.out.println(response);
        List<Student> studentList=crudService.getAll();

        if (request.getParameter("delete")!=null){
            doDelete(request,response);
        }

        String list="";
        for (Student student : studentList) {
            System.out.println(student.toString());
            list+="<tr>\n" +
                    "                    <form action=\"/\" method=\"post\">\n" +
                    "                        <td>"+student.getId()+"</td>\n" +
                    "                        <input value="+student.getId()+" type=\"hidden\" placeholder=\"id\" name=\"id\">\n" +
                    "\n" +
                    "\n" +
                    "                        <td>\n" +
                    "\n" +
                    "                            <input value=\""+student.getName()+"\" placeholder=\"name\" name=\"name\" class=\"form-control\">\n" +
                    "\n" +
                    "                        </td>\n" +
                    "\n" +
                    "                        <td>\n" +
                    "\n" +
                    "                            <input value=\""+student.getSurname()+"\" placeholder=\"surname\" name=\"surname\" class=\"form-control\">\n" +
                    "\n" +
                    "                        </td>\n" +
                    "\n" +
                    "                        <td class=\"text-center\">\n" +
                    "                            <button type=\"submit\" class=\"btn btn-success\">Yangilash</button>\n" +
                    "                            <a href='?delete="+student.getId()+"' type=\"submit\" class=\"btn btn-danger\">O'chirish</a>\n" +
                    "                        </td>\n" +
                    "\n" +
                    "                    </form>\n" +
                    "\n" +
                    "                </tr>";
        }

        String main="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                "\n" +
                "<style>\n" +
                "    table {\n" +
                "        font-family: arial, sans-serif;\n" +
                "        border-collapse: collapse;\n" +
                "        width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    td, th {\n" +
                "        border: 1px solid #dddddd;\n" +
                "        text-align: left;\n" +
                "        padding: 8px;\n" +
                "    }\n" +
                "\n" +
                "    tr:nth-child(even) {\n" +
                "        background-color: #dddddd;\n" +
                "    }\n" +
                "</style>\n" +
                "<body style=\"background-color: lightgrey\">\n" +
                "\n" +
                "<section class=\"h-100\" >\n" +
                "    <div class=\"card h-100 container-fluid\">\n" +
                "\n" +
                "        <div class=\"card-header text-center\">\n" +
                "            <h3>\n" +
                "                Studentlar\n" +
                "            </h3>\n" +
                "\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"card-body\" >\n" +
                "\n" +
                "            <table class=\"table\">\n" +
                "                <tr class=\"\">\n" +
                "                    <td>\n" +
                "                        Yangi student qo'shish:\n" +
                "                    </td>\n" +
                "\n" +
                "                    <form action=\"/\" method=\"post\">\n" +
                "                        <td>\n" +
                "                            <input class=\"form-control\" id=\"name\" name=\"name\"  type=\"text\" placeholder=\"ism\">\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input class=\"form-control\" id=\"surname\" name=\"surname\"  type=\"text\" placeholder=\"familiya\">\n" +
                "                        </td>\n" +
                "                        <td class=\"text-center\">\n" +
                "                            <button type=\"submit\" class=\"btn btn-success\" style=\"\">Yangi qo'shish</button>\n" +
                "                        </td>\n" +
                "                    </form>\n" +
                "\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr class=\"\">\n" +
                "                    <th>id</th>\n" +
                "                    <th>Ismi</th>\n" +
                "                    <th>Familiyasi</th>\n" +
                "                    <th>Tools</th>\n" +
                "                </tr>\n" +
                list+
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>";

        printWriter.println(main);

    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student=new Student();
        student.setId(Long.valueOf(request.getParameter("id")));
        student.setName(request.getParameter("name"));
        student.setSurname(request.getParameter("surname"));
        crudService.put(student);
        response.sendRedirect("/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        crudService.delete(Long.valueOf(request.getParameter("delete")));
        response.sendRedirect("/");
    }
}
