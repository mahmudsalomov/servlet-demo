import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudService {


    private final String url="jdbc:postgresql://localhost:5432/student";
    private final String user="postgres";
    private final String password="postgres";

    Connection connection=null;
    Statement statement=null;
    String query="";

    public CrudService() throws SQLException {
    }
    public List<Student> getAll(){
        List<Student> studentList=new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            query="select * from students order by id";
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                Student student=new Student(resultSet.getLong(1),resultSet.getString(2), resultSet.getString(3));
                studentList.add(student);
            }
            statement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }



    public void post(Student student){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            query="insert into students (name, surname) values ('"+student.getName()+"','"+student.getSurname()+"')";
            statement.execute(query);

            //            String add="insert into books (title,author) values ('Isrof','Shayx Muhammad Sodiq Muhammad Yusuf')";
//            Statement statement=connection.createStatement();
//            statement.execute(add);
//            statement.close();
//            connection.close();
            statement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void put(Student student){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            query="update students set name='"+student.getName()+"', surname='"+student.getSurname()+"' where id="+student.getId();
            statement.execute(query);

            //            String add="insert into books (title,author) values ('Isrof','Shayx Muhammad Sodiq Muhammad Yusuf')";
//            Statement statement=connection.createStatement();
//            statement.execute(add);
//            statement.close();
//            connection.close();
            statement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            query="delete from students where id="+id;
            statement.execute(query);

            //            String add="insert into books (title,author) values ('Isrof','Shayx Muhammad Sodiq Muhammad Yusuf')";
//            Statement statement=connection.createStatement();
//            statement.execute(add);
//            statement.close();
//            connection.close();
            statement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
