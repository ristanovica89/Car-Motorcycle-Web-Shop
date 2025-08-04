package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import models.Vehicle;
import models.VehicleStatus;
import models.VehicleType;

public class VehicleDao {

private DataSource ds;
	
	
	
	private final static String ALL_VEHICLES_FOR_ADMIN = "SELECT v.v_id, b.brand_name, m.model_name, v.type, v.year, v.price, v.status FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id;";
	private final static String ALL_VEHICLES_FOR_USER = "SELECT v.v_id, b.brand_name, m.model_name, v.type, v.year, v.price FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE v.status = 'APPROVED';";
	private final static String GET_VEHICLE_BY_ID = "SELECT v.v_id, b.brand_name, m.model_name, v.type, v.year, v.price, v.status FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE v.v_id = ?;";
	
	private final static String INIT_DB = "INSERT INTO `vehicles` (`v_id`, `type`, `model_id`, `year`, `price`, `status`) VALUES (NULL, 'CAR', '7', '2005', '1800', 'APPROVED'), (NULL, 'CAR', '8', '2016', '2600', 'APPROVED'), (NULL, 'MOTORCYCLE', '11', '2020', '15500', 'PENDING'), (NULL, 'MOTORCYCLE', '12', '2022', '17800', 'APPROVED'), (NULL, 'CAR', '9', '2017', '2000', 'PENDING');";
    
	private final static String EMPTY_DB = "DELETE FROM vehicles";
    private final static String RESET_AI = "ALTER TABLE vehicles AUTO_INCREMENT = 1";
	private static String SEARCH = "Select v.v_id, b.brand_name, m.model_name, v.type, v.year, v.price, v.status FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE 1=1";
    
	private static final String GET_ALL_BRAND_CAR = "SELECT DISTINCT b.brand_name FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE v.type = 'CAR'";
	private static final String GET_ALL_BRAND_MOTORCYCLE = "SELECT DISTINCT b.brand_name FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE v.type = 'MOTORCYCLE'";

	private static final String MODELS_OF_BRAND = "SELECT DISTINCT m.model_name FROM vehicles v JOIN model m ON v.model_id = m.model_id JOIN brand b ON m.brand_id = b.brand_id WHERE b.brand_name = ?";
	private static final String DELETE_VEHICLE = "DELETE FROM vehicles WHERE v_id = ?";
	private static final String UPDATE_VEHICLE = "UPDATE vehicles SET year = ?, price = ?, status = ? WHERE v_id = ?";
	
	private static final String UPLOAD_VEHICLE = "INSERT INTO vehicles (type, model_id, year, price) VALUES (?, ?, ?, ?)";

	private static final String GET_MODEL_ID_FROM_MODEL_NAME = "SELECT model_id FROM model WHERE model_name = ?";
	
	public VehicleDao(){
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
	
	public ArrayList<Vehicle> getAllVehiclesForAdmin(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<Vehicle> listOfVehicles = new ArrayList<Vehicle>();
			Vehicle vehicle = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ALL_VEHICLES_FOR_ADMIN);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				vehicle = new Vehicle();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				vehicle.setVehicleId(rs.getInt("v_id"));
				vehicle.setBrand(rs.getString("brand_name"));
				vehicle.setModel(rs.getString("model_name"));
				String stype = rs.getString("type");
				vehicle.setType(VehicleType.valueOf(stype.toUpperCase()));
				vehicle.setYear(rs.getInt("year"));
				vehicle.setPrice(rs.getDouble("price"));
				String sstatus = rs.getString("status");
				vehicle.setStatus(VehicleStatus.valueOf(sstatus.toUpperCase()));
				
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfVehicles.add(vehicle);
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
		return listOfVehicles; 
	}
	
	public ArrayList<Vehicle> getAllVehiclesForUser(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<Vehicle> listOfVehicles = new ArrayList<Vehicle>();
			Vehicle vehicle = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ALL_VEHICLES_FOR_USER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				vehicle = new Vehicle();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				vehicle.setVehicleId(rs.getInt("v_id"));
				vehicle.setBrand(rs.getString("brand_name"));
				vehicle.setModel(rs.getString("model_name"));
				String stype = rs.getString("type");
				vehicle.setType(VehicleType.valueOf(stype.toUpperCase()));
				vehicle.setYear(rs.getInt("year"));
				vehicle.setPrice(rs.getDouble("price"));
				vehicle.setStatus(VehicleStatus.APPROVED);
				
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfVehicles.add(vehicle);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfVehicles; 
	}
	
	public Vehicle getVehicleById(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			Vehicle vehicle = null;
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_VEHICLE_BY_ID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setInt(1, id);
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				vehicle = new Vehicle();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				vehicle.setVehicleId(rs.getInt("v_id"));
				vehicle.setBrand(rs.getString("brand_name"));
				vehicle.setModel(rs.getString("model_name"));
				String stype = rs.getString("type");
				vehicle.setType(VehicleType.valueOf(stype.toUpperCase()));
				vehicle.setYear(rs.getInt("year"));
				vehicle.setPrice(rs.getDouble("price"));
				String sstatus = rs.getString("status");
				vehicle.setStatus(VehicleStatus.valueOf(sstatus.toUpperCase()));
				
				
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
		return vehicle; 
	}
	
	public ArrayList<Vehicle> searchForVehiclesForAdmin(
			VehicleType type,
			int yearFrom,
			int yearTo,
			String brand,
			String model
			){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<Vehicle> listOfVehicles = new ArrayList<Vehicle>();
			Vehicle vehicle = null;
			
			StringBuilder sql = new StringBuilder(SEARCH);
			
			if(type.equals(VehicleType.CAR)) {
				sql.append(" AND type='CAR'");
			} else {
				sql.append(" AND type='MOTORCYCLE'");
			}
			
			if(yearFrom != 0) {
				sql.append(" AND year >= ?");
			}
			
			if(yearTo != 0) {
				sql.append(" AND year <= ?");
			}
			
			if(brand != null && !brand.isEmpty()) {
				sql.append(" AND brand_name = ?");
			}
			
			if(model != null && !model.isEmpty()) {
				sql.append(" AND model_name = ?");
			}
			
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(sql.toString());

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
			int index = 1;
			
			if(yearFrom != 0) {
				pstm.setInt(index++, yearFrom);
			}
			
			if(yearTo != 0) {
				pstm.setInt(index++, yearTo);
			}
			
			if(brand != null && !brand.isEmpty()) {
				pstm.setString(index++, brand);
			}
			
			if(model != null && !model.isEmpty()) {
				pstm.setString(index++, model);
			}
			
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				vehicle = new Vehicle();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				vehicle.setVehicleId(rs.getInt("v_id"));
				vehicle.setBrand(rs.getString("brand_name"));
				vehicle.setModel(rs.getString("model_name"));
				String stype = rs.getString("type");
				vehicle.setType(VehicleType.valueOf(stype.toUpperCase()));
				vehicle.setYear(rs.getInt("year"));
				vehicle.setPrice(rs.getDouble("price"));
				String sstatus = rs.getString("status");
				vehicle.setStatus(VehicleStatus.valueOf(sstatus.toUpperCase()));
				
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfVehicles.add(vehicle);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfVehicles; 
	}
	
	public ArrayList<String> getAllBrandNameCars(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<String> listOfBrand = new ArrayList<String>();
			String brand = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL_BRAND_CAR);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				brand =rs.getString("brand_name");

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfBrand.add(brand);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfBrand; 
	}
	
	public ArrayList<String> getAllBrandNameMotorcycle(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<String> listOfBrand = new ArrayList<String>();
			String brand = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_ALL_BRAND_MOTORCYCLE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				brand =rs.getString("brand_name");

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfBrand.add(brand);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfBrand; 
	}
	
	public ArrayList<String> getAllModelsOfABrand(String brand){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
			ArrayList<String> listOfModels = new ArrayList<String>();
			String model = null;
			
				
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(MODELS_OF_BRAND);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, brand);
				pstm.execute();

			//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs

				model =rs.getString("model_name");

				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				listOfModels.add(model);
			}
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}finally {
		    try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return listOfModels; 
	}
	
	public boolean initDb(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		int rowsAffected = 0;
			// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
					
				try {
				con = ds.getConnection();
				pstm = con.prepareStatement(INIT_DB);

				// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
					
					rowsAffected = pstm.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
			return rowsAffected == 5; 
		}
	
	public boolean empty() {
	    Connection con = null;
	    PreparedStatement deletePstm = null;
	    PreparedStatement resetPstm = null;
	    int rowsDeleted = 0;

	    try {
	        con = ds.getConnection();
	        
	        deletePstm = con.prepareStatement(EMPTY_DB);
	        rowsDeleted = deletePstm.executeUpdate();

	        resetPstm = con.prepareStatement(RESET_AI);
	        resetPstm.executeUpdate();
	        
	        

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        try { if (deletePstm != null) deletePstm.close(); } catch (SQLException ignore) {}
	        try { if (resetPstm != null) resetPstm.close(); } catch (SQLException ignore) {}
	        try { if (con != null) con.close(); } catch (SQLException ignore) {}
	    }

	    return rowsDeleted > 0;
	}
	
	public boolean deleteVehiclebyId(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		int rowsDeleted = 0;
		//ResultSet rs = null;
		
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETE_VEHICLE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			rowsDeleted = pstm.executeUpdate();
			
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return rowsDeleted > 0;
	}
	
	public boolean updateVehicleById(int id, int year, double price, VehicleStatus status){
		Connection con = null;
		PreparedStatement pstm = null;
		int rowsAffected = 0;
		//ResultSet rs = null;
		
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATE_VEHICLE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1,year);
			pstm.setDouble(2, price);
			pstm.setString(3, status.name());
			pstm.setInt(4, id);
			rowsAffected = pstm.executeUpdate();
			
			
			//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { if (pstm != null) pstm.close(); } catch (SQLException ignore) {}
		    try { if (con != null) con.close(); } catch (SQLException ignore) {}
		}
		return rowsAffected > 0;
	}
	
	public int getModelIdFromModelName(String modelName){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		int modelId = 0;		
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GET_MODEL_ID_FROM_MODEL_NAME);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, modelName);
			
				rs = pstm.executeQuery();

	        if (rs.next()) {
	            modelId = rs.getInt("model_id");
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
		return modelId; 
	}
	
	public boolean insertNewVehicle(VehicleType type, int modelId, int year, double price ) {
		
		Connection con = null;
		PreparedStatement pstm = null;
		
			// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 	
		boolean success = false;
		
			try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPLOAD_VEHICLE);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
				pstm.setString(1, type.name());
				pstm.setInt(2, modelId);
				pstm.setInt(3, year);
				pstm.setDouble(4, price);
				
				int rowsAffected = pstm.executeUpdate();
			 
		            success = rowsAffected > 0;
		            
			     
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
			return success; 
	}
		
}
