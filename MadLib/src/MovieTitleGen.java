import java.net.URL;
import java.sql.ResultSet;
import java.util.Scanner;

public class MovieTitleGen
{
	public static void main(String[] args) throws Exception
	{
		MovieDB.openConnection();
		MovieDB movie = new MovieDB();
		Scanner sc = new Scanner(System.in);
		String movietitle= "";
		int id = 1;
		System.out.println("Myxyllplyk's Random Movie Title Generator\n");

		movietitle = movie.getTitles();
		System.out.println("Your random movie title is: " + movietitle);
		
		System.out.println("Please Provide Description of Movie (100 char) :");
		String desc = sc.nextLine();
		movie.SetDesc(movietitle, desc);
		
		
		MovieDB.conn.close();
	}

}

