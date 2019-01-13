package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.MyFrame;


/**
 * this class has all the data of all the games that have been played on this server,
 * saves the id's of the players, the time they played, their scores in each game and the id of the game they played.
 * then it prints in the console after the game is over what is our place in the records table comparing our other games 
 * and comparing to the other players in this game.
 * @author Inna and Chen
 *
 */
public class Sql {

	private double d1 = 2.12825983E9;
	private double d2 = 1.149748017E9;
	private double d3 = -6.8331707E8;
	private double d4 = 1.193961129E9;
	private double d5 = 1.577914705E9;
	private double d6 = -1.315066918E9;
	private double d7 = -1.377331871E9;
	private double d8 = 3.06711633E8;
	private double d9 = 9.19248096E8;

	private	double LastGameScore = 0;
	private double compareDouble = 0;

	/**
	 * returns the sql data, comparing our game results with the entire class results
	 */
	public void getData()
	{
		System.out.println("here");
		
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

			Statement statement = connection.createStatement();


			String myGamesQuery= "Select * from logs WHERE FirstID="+MyFrame.getPlayerId()+" ORDER BY LogTime DESC LIMIT 1"; 
			ResultSet resultSet = statement.executeQuery(myGamesQuery);
			resultSet.last(); //the last game we played
			System.out.println("\nFirstID\t\tLogTime\t\t\tPoint\tSomeDouble");
			System.out.println(resultSet.getInt("FirstID")+"\t" + resultSet.getTimestamp("LogTime") +"\t" +
					resultSet.getDouble("Point") +"\t" + resultSet.getDouble("SomeDouble"));

			LastGameScore = resultSet.getDouble("Point");
			compareDouble = resultSet.getDouble("SomeDouble");
			//all our games
			String allMyGamesQuery = "Select * from logs WHERE FirstID="+MyFrame.getPlayerId()+" AND SomeDouble = " + compareDouble +" ORDER BY Point";
			resultSet = statement.executeQuery(allMyGamesQuery);

			System.out.println("\nFirstID\t\tLogTime\t\t\tPoint\tSomeDouble");
			//comparing our last game to our other games
			int i = 1;
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt("FirstID")+"\t\t" + resultSet.getTimestamp("LogTime") +"\t" +
						resultSet.getDouble("Point") +"\t" + resultSet.getDouble("SomeDouble"));

				if(resultSet.getDouble("Point") > LastGameScore)
					i++;
				else
					break;

			}
			System.out.println("\nOur points in game number "+getGameId(compareDouble)+" are "+resultSet.getDouble("Point")
			+" and we reached the " + i + " place compared to the other games we played");

			//entire class playing the same game 
			String allGamesQuery = "SELECT * FROM logs WHERE LogTime >= '2018-12-26' AND SomeDouble="+compareDouble + " ORDER BY point DESC";
			resultSet = statement.executeQuery(allGamesQuery);
			System.out.println("\nFirstID\t\tSecondID\t\tThirdID\t\tLogTime\t\t\t\tPoint\t\t\tSomeDouble");
			i = 1;
			//comparing our last game to all the other class played the same game
			while (resultSet.next())
			{
				System.out.println(resultSet.getInt("FirstID")+"\t\t" + resultSet.getInt("SecondID")+"\t\t" + resultSet.getInt("ThirdID")+"\t\t" +
						resultSet.getTimestamp("LogTime") +"\t\t\t\t" + resultSet.getDouble("Point") +"\t\t" + resultSet.getDouble("SomeDouble"));

				if(resultSet.getDouble("Point") > LastGameScore)
					i++;
				else
					break;
			}

            System.out.println("\nOur points in game number "+getGameId(compareDouble)+" are "+resultSet.getDouble("Point") + 
            					" and we reached the " + i + " place compared to the same game the entire class played");

			resultSet.close();
			
			statement.close();	
			connection.close();		
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	private int getGameId(double someDouble) {
		if(someDouble == d1)
			return 1;
		else if(someDouble == d2)
			return 2;
		else if(someDouble == d3)
			return 3;
		else if(someDouble == d4)
			return 4;
		else if(someDouble == d5)
			return 5;
		else if(someDouble == d6)
			return 6;
		else if(someDouble == d7)
			return 7;
		else if(someDouble == d8)
			return 8;
		else if(someDouble == d9)
			return 9;
		return -1;
	}

}
