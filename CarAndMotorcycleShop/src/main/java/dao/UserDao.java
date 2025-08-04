package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.User;
import models.UserRole;



public class UserDao {
	
	private DataSource ds;
	
	
	private final static String ALL_USERS = "SELECT user_id,username FROM users WHERE role = 'USER'";
	private final static String ALL_USERS_FOR_ADMIN = "SELECT user_id,username,role FROM users";
	private final static String LOGIN_USER = "SELECT user_id,username,role FROM users WHERE username=? AND password=?";
	private final static String SIGNUP_USER = "INSERT INTO `users`(`username`, `password`) VALUES (?,?)";
	private final static String ADD_USER = "INSERT INTO `users`(`username`, `password`,`role`) VALUES (?,?,?)";
	private final static String CHECK_BY_USERNAME = "SELECT 1 FROM users WHERE username = ?";
	private final static String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

	
	public UserDao(){
		try {
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) { 
			} 
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
			if ( ds == null ) { 
			} 		
			} catch (NamingException e) {
			}
		}
	
	public ArrayList<User> getAllUsers(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<User> listOfUsers = new ArrayList<User>();
			User user = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ALL_USERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfUsers.add(user);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfUsers; 
	}
	
	public ArrayList<User> getAllUsersForAdmin(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<User> listOfUsers = new ArrayList<User>();
			User user = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ALL_USERS_FOR_ADMIN);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				String srole = rs.getString("role");
				user.setRole(UserRole.valueOf(srole.toUpperCase()));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfUsers.add(user);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfUsers; 
	}
	
		
	public User login(String username, String password) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			User user = null;
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(LOGIN_USER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, username);
				pstm.setString(2, password);
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

				if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				user = new User();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				String roleS = rs.getString("role");
				user.setRole(UserRole.valueOf(roleS.toUpperCase()));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				//listOfUsers.add(user);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return user;	
	}
	
  public boolean checkUserByUsername(String username){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean result = false;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(CHECK_BY_USERNAME);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, username);
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ 
				result = true;
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return result; 
	}
  
  public User signup(User user) {
			
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 	
		try {
		con = ds.getConnection();
		pstm = con.prepareStatement(SIGNUP_USER, Statement.RETURN_GENERATED_KEYS);

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			
			int rowsAffected = pstm.executeUpdate();
		 
			if (rowsAffected > 0) {
	            rs = pstm.getGeneratedKeys();
	            if (rs.next()) {
	                int generatedId = rs.getInt(1);
	                user.setUserId(generatedId);
	            }
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return user; 
	}
  
  public User getUserByUsername(String username) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
			// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 	
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_USER_BY_USERNAME);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, username);			
				pstm.execute();
			 
				rs = pstm.getResultSet();

				while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
					// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
					user = new User();
					// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

					user.setUserId(rs.getInt("user_id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					String roleS = rs.getString("role");
					user.setRole(UserRole.valueOf(roleS.toUpperCase()));
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
			return user; 
		}
  
  
  
  public User addUser(User user) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
			// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 	
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, user.getUsername());
				pstm.setString(2, user.getPassword());
				pstm.setString(3, user.getRole().name());
				
				int rowsAffected = pstm.executeUpdate();
			 
				if (rowsAffected > 0) {
		            rs = pstm.getGeneratedKeys();
		            if (rs.next()) {
		                int generatedId = rs.getInt(1);
		                user.setUserId(generatedId);
		            }
			     }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
			return user; 
		}
  
}

