import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudService {


    private final String url="jdbc:postgresql://localhost:5432/student";
    private final String user="postgres";
    private final String password="postgres";

    Connection connection=null;
    Statement statement=null;
    PreparedStatement preparedStatement=null;
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



    public boolean post(Student student){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select count(*) from students");
            while (resultSet.next()){
                if (resultSet.getInt(1)>50){
                    return false;
                }
            }
            query="insert into students (name, surname) values (?,?)";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getSurname());
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void put(Student student){
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            query="update students set name=?, surname=? where id=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getSurname());
            preparedStatement.setLong(3, student.getId());
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();

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
            statement.close();
            connection.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
