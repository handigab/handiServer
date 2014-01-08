package handiGAB_server;

//import handiGAB_server.SQLiteJDBC;

public class handiGAB_server {

	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		System.out.println("Hello world !");
		
		SQLiteJDBC con = new SQLiteJDBC();
		System.out.println(con.db_get_client_name("111"));
		System.out.println(con.db_get_client_balance("111"));
		System.out.println(con.db_get_client_name("222"));
		System.out.println(con.db_get_client_balance("222"));
		System.out.println(con.db_get_client_name("AA"));
		System.out.println(con.db_get_client_balance("AA"));
		
		con.db_create_account("111", "Paul", 133.43);
		System.out.println(con.db_get_client_name("111"));
		System.out.println(con.db_get_client_balance("111"));
		
		con.db_withdraw_fom_account("111", 160.0);
		System.out.println(con.db_get_client_name("111"));
		System.out.println(con.db_get_client_balance("111"));
		
		con.db_withdraw_fom_account("111", 133.0);
		System.out.println(con.db_get_client_name("111"));
		System.out.println(con.db_get_client_balance("111"));
		
		

		
		
		
		
		
	}

}
