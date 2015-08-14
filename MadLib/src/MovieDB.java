import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MovieDB {

	static Connection conn;
	
	public static void openConnection()
	{
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:testuser/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        try {
			conn = DriverManager.getConnection(url,props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getFromDB(String sql)throws SQLException 
	{
		PreparedStatement preStatement = conn.prepareStatement(sql);
	    
        ResultSet result = preStatement.executeQuery();
        
        return result;
	}
	
	public void UpdateDB(String sql) throws SQLException 
	{

        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql);
    
        preStatement.executeUpdate();

	}
	
	public String getTitles() throws SQLException 
	{
		
        String sql ="SELECT * FROM (SELECT * FROM titles ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum =1";

        ResultSet result= getFromDB(sql);
        result.next();
        System.out.println(result.getString("Adj"));
        String movie =result.getString("Adj") + " " + result.getString("Noun");
		return movie;
	}
	
	public void SetDesc(String movietitle, String desc  )
	{
		//int ID = id;
		String description = desc;
		String movie = movietitle;
		
		String sql = "Insert into storage (id, title, description) VALUES (" + "seq_title.nextVal" +  ",'" + movie + "', '" + description + "')";
		
		try {
			UpdateDB(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
