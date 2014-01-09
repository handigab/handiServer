package handiGAB_server;

import java.io.File;
import java.sql.*;

public class SQLiteJDBC
{
	private Connection connection;
	
	public SQLiteJDBC(){
		connection = db_open_connection();
		db_initialization();
	}
	
	public Connection db_open_connection()
	{
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:bankDataBase.db");
			c.setAutoCommit(false);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}
	
	public void db_initialization(){

		File f = new File("bankDataBase.db");
		Statement stmt = null;

		if(f.exists()){
			System.out.println("Data base already exists");
			if(f.length() != 0){
				System.out.println("Data base already contains informations. No initialization.");
			}else{
				System.out.println("Data base is empty. Tables creation and add of entries.");
				try{
					stmt = connection.createStatement();
					//Table creation
					String command = "CREATE TABLE Customer(id char(20) PRIMARY KEY UNIQUE, name char(50), balance decimal(10,2))";
					stmt.executeUpdate(command);
					//Add of customer one
					command = "INSERT INTO Customer (id, name, balance) VALUES (\"111\", \"Roux\", 150.00)";
					stmt.executeUpdate(command);
					
					//Add of customer two
					command = "INSERT INTO Customer (id, name, balance) VALUES (\"222\", \"Bernadette\", 22.00)";
					stmt.executeUpdate(command);
					
					stmt.close();
					connection.commit();
					
				} catch ( Exception e ) {
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					System.exit(0);
				}
			}	
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
	}
	
	public void db_create_account(String id, String name, double balance){

		File f = new File("bankDataBase.db");
		Statement stmt = null;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "SELECT id FROM Customer WHERE id = \""+id+"\"";
				ResultSet res = stmt.executeQuery(command);
				connection.commit();
				if(res.next()){
					System.out.println("UPDATE");
					command = "UPDATE Customer SET name=\""+name+"\", balance="+balance+" WHERE id=\""+id+"\"";
					stmt.executeUpdate(command);
				}else{
					System.out.println("CREATE");
					command = "INSERT INTO Customer (id, name, balance) VALUES (\""+id+"\", \""+name+"\", "+balance+")";
					stmt.executeUpdate(command);
				}
				stmt.close();
				connection.commit();

			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
	}
	
	public String db_get_client_name(String id){
		
		File f = new File("bankDataBase.db");
		Statement stmt = null;
		String name;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "SELECT name FROM Customer WHERE id = \""+id+"\"";
				ResultSet res = stmt.executeQuery(command);
				name = res.getString("name");				
				stmt.close();
				connection.commit();
				return name;

			} catch ( Exception sQLException ) {
				return "";
			} 
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
		return "";
	}
	
	public double db_get_client_balance(String id){
		
		File f = new File("bankDataBase.db");
		Statement stmt = null;
		double balance;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "SELECT balance FROM Customer WHERE id = \""+id+"\"";
				ResultSet res = stmt.executeQuery(command);
				balance = res.getDouble("balance");				
				stmt.close();
				connection.commit();
				return balance;

			} catch ( Exception sQLException ) {
				return -1;
			} 
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
		return -1;
	}
	
	/*public int db_get_client_perso(String id){

		File f = new File("bankDataBase.db");
		Statement stmt = null;
		int perso;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "SELECT perso FROM Customer WHERE id = \""+id+"\"";
				ResultSet res = stmt.executeQuery(command);
				perso = res.getInt("perso");				
				stmt.close();
				connection.commit();
				return perso;

			} catch ( Exception sQLException ) {
				return -1;
			} 
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
		return -1;
	}
	*/

	public boolean db_withdraw_fom_account(String id, double amount){
		double balance = db_get_client_balance(id);
		if((balance-amount) < 0){
			return false;
		}else{
			db_update_balance(id, balance-amount);
			return true;
		}
	}
	
	public void db_update_balance(String id, double balance){

		File f = new File("bankDataBase.db");
		Statement stmt = null;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "UPDATE Customer SET balance="+balance+" WHERE id=\""+id+"\"";
				stmt.executeUpdate(command);
				stmt.close();
				connection.commit();

			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
	}
	
	public boolean db_test_account(String id){

		File f = new File("bankDataBase.db");
		Statement stmt = null;
		boolean test = false;

		if(f.exists()){
			try{
				stmt = connection.createStatement();
				String command = "SELECT id FROM Customer WHERE id = \""+id+"\"";
				ResultSet res = stmt.executeQuery(command);
				connection.commit();
				if(res.next()){
					test = true;
				}
				stmt.close();

			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}else{
			System.out.println("Data base not found!");
			System.exit(0);
		}
		
		return test;
	}
}

