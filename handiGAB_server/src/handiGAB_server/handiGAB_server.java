package handiGAB_server;

import handiGAB_server.incomingSocket;

public class handiGAB_server {

	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		System.out.println("Hello world !");
		
		//SQLiteJDBC con = new SQLiteJDBC();
		/*System.out.println(con.db_get_client_name("047A8AF2422580"));
		System.out.println(con.db_get_client_balance("047A8AF2422580"));
		System.out.println(con.db_get_client_perso("047A8AF2422580"));
		System.out.println(con.db_get_client_name("04B277F2422580"));
		System.out.println(con.db_get_client_balance("04B277F2422580"));
		System.out.println(con.db_get_client_perso("04B277F2422580"));
		System.out.println(con.db_get_client_name("AA"));
		System.out.println(con.db_get_client_balance("AA"));
		System.out.println(con.db_get_client_perso("AA"));

		
		con.db_create_account("047A8AF2422580", "Paul", 133.43, 1);
		System.out.println(con.db_get_client_name("047A8AF2422580"));
		System.out.println(con.db_get_client_balance("047A8AF2422580"));
		System.out.println(con.db_get_client_perso("047A8AF2422580"));

		
		con.db_create_account("555", "Pierre", 2232.22, 0);
		System.out.println(con.db_get_client_name("555"));
		System.out.println(con.db_get_client_balance("555"));
		System.out.println(con.db_get_client_perso("555"));

		
		con.db_withdraw_fom_account("047A8AF2422580", 160.0);
		System.out.println(con.db_get_client_name("047A8AF2422580"));
		System.out.println(con.db_get_client_balance("047A8AF2422580"));
		System.out.println(con.db_get_client_perso("047A8AF2422580"));
		
		con.db_withdraw_fom_account("047A8AF2422580", 133.0);
		System.out.println(con.db_get_client_name("047A8AF2422580"));
		System.out.println(con.db_get_client_balance("047A8AF2422580"));
		System.out.println(con.db_get_client_perso("047A8AF2422580"));
		
		System.out.println(con.db_test_account("555"));
		System.out.println(con.db_test_account("55"));
		*/
		
		incomingSocket.listeningRoutine();	
		
	}

}
