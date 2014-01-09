package handiGAB_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class incomingSocket {
	static final int port = 6942;

	private enum operation{
		WHITDRAW,
		TEST_CLIENT,
		ADD_CLIENT,
		GET_NAME,
		GET_BALANCE		
	}
	
	static public void listeningRoutine() {
		ServerSocket s;
		SQLiteJDBC con = new SQLiteJDBC();
		String id, name;
		double amount;
		try {
			s = new ServerSocket(port);

			try {

				while(true){
					System.out.println("Socket serveur: " + s);
					Socket soc = s.accept();
					System.out.println("Serveur a accepte connexion: " + soc);

					
					System.out.println("Serveur a cree les flux");
					ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
					out.flush();
					Object tmp = in.readObject();
					operation operationType = (operation) operation.values()[(int)tmp];
					System.out.println("Serveur recoit: " + operationType);
					try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					switch(operationType){
					case WHITDRAW:
						System.out.println("WHITDRAW!!!");
						id = (String)in.readObject();
						amount = (double)in.readObject();						
						boolean resultWhitdraw = con.db_withdraw_fom_account( id, amount);						
						out.writeObject(resultWhitdraw);
						out.flush();	
						break;
					case TEST_CLIENT:
						System.out.println("TEST CLIENT!!!");
						id = (String)in.readObject();
						boolean resultTestClient = con.db_test_account(id);
						out.writeObject(resultTestClient);
						out.flush();
						break;
					case ADD_CLIENT:
						System.out.println("ADD CLIENT");
						id = (String)in.readObject();
						name = (String)in.readObject();
						amount = (double)in.readObject();
						con.db_create_account(id, name, amount);
						break;
					case GET_NAME:
						System.out.println("GET NAME");
						id = (String)in.readObject();
						String resultName = con.db_get_client_name(id);
						out.writeObject(resultName);
						out.flush();
						break;
					case GET_BALANCE:
						System.out.println("GET BALANCE");
						id = (String)in.readObject();
						Double resultBalance = con.db_get_client_balance(id);
						out.writeObject(resultBalance);
						out.flush();
						break;
					default:
						System.out.println("NOP ! CHUCK TESTA !!!");
						//-1
					}
					in.close();
					out.close();
					soc.close();
					
				}

			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		} catch (IOException e1) {
			System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
			System.exit(0);
		}
    }
}
