import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CrudService crudService=new CrudService();
        System.out.println(crudService.getAll());
    }
}
