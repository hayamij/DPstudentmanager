package vn.edu.giadinh.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
// import java.util.Date;
import java.util.List;

import vn.edu.giadinh.business.EconomicsStudent;
import vn.edu.giadinh.business.SoftwareStudent;
import vn.edu.giadinh.business.Student;

public class StudentListViewDAO {
	
	public StudentListViewDAO() throws ClassNotFoundException, SQLException {
		try {
			//load driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver loaded successfully!");
			
			String server = "phuongtuan"; // Thay đổi thành localhost hoặc địa chỉ server thực tế
			String database = "test";
			String username = "fuongtuan"; // Thay đổi thành username thực tế
			String password = "toilabanhmochi"; // Thay đổi thành password thực tế
			String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + database +";trustServerCertificate=true;encrypt=true";
			
			System.out.println("Attempting to connect to: " + url);
			Connection testConn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected successfully to database!");
			testConn.close();
			
		} catch (ClassNotFoundException e) {
			System.err.println("SQL Server Driver not found!");
			System.err.println("Make sure mssql-jdbc jar file is in lib folder and in classpath");
			throw e;
		} catch (SQLException e) {
			System.err.println("Database connection failed!");
			System.err.println("Please check:");
			System.err.println("1. SQL Server is running");
			System.err.println("2. Database 'test' exists");
			System.err.println("3. Username and password are correct");
			System.err.println("4. Port 1433 is accessible");
			throw e;
		}
	}
	
	public List<StudentDTO> getAll() throws SQLException, ParseException{
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String server = "phuongtuan";
			String database = "test";
			String username = "fuongtuan";
			String password = "toilabanhmochi";
			String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + database +";trustServerCertificate=true;encrypt=true";
			
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "select id, name, birthday, major, javaScore,"
			+ "htmlScore, cssScore, marketing, salesScore "
			+ "from student";
			//tạo đối tượng statement để thực thi câu query trong java
			stmt = conn.createStatement();
			//thực thi câu query
			rs = stmt.executeQuery(sql);
			
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

			//duyệt từng sv trong rs
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.id = rs.getString("id");
				dto.name = rs.getString("name");
				dto.birthDate = fmt.parse(rs.getString("birthday"));
				dto.major = rs.getString("major");
				dto.javaScore = rs.getDouble("javaScore");
				dto.htmlScore = rs.getDouble("htmlScore");
				dto.cssScore = rs.getDouble("cssScore");
				dto.marketingScore = rs.getDouble("marketing");
				dto.salesScore = rs.getDouble("salesScore");
				list.add(dto);
			}
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		
		return list;
	}
	
	public boolean insert(Student student) {
		try {
			// Tạo connection mới cho insert
			String server = "phuongtuan";
			String database = "test";
			String username = "fuongtuan";
			String password = "toilabanhmochi";
			String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + database +";trustServerCertificate=true;encrypt=true";
			
			Connection insertConn = DriverManager.getConnection(url, username, password);
			
			String sql = "INSERT INTO student (id, name, birthday, major, javaScore, htmlScore, cssScore, marketing, salesScore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			java.sql.PreparedStatement pstmt = insertConn.prepareStatement(sql);
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
			pstmt.setString(4, student.getMajor());
			
			if (student instanceof SoftwareStudent) {
				SoftwareStudent sw = (SoftwareStudent) student;
				pstmt.setDouble(5, sw.getJavaScore());
				pstmt.setDouble(6, sw.getHtmlScore());
				pstmt.setDouble(7, sw.getCssScore());
				pstmt.setNull(8, java.sql.Types.DECIMAL);
				pstmt.setNull(9, java.sql.Types.DECIMAL);
			} else if (student instanceof EconomicsStudent) {
				EconomicsStudent ec = (EconomicsStudent) student;
				pstmt.setNull(5, java.sql.Types.DECIMAL);
				pstmt.setNull(6, java.sql.Types.DECIMAL);
				pstmt.setNull(7, java.sql.Types.DECIMAL);
				pstmt.setDouble(8, ec.getMarketingScore());
				pstmt.setDouble(9, ec.getSalesScore());
			}
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			insertConn.close();
			
			return result > 0;
			
		} catch (SQLException e) {
			System.err.println("Error inserting student: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	// public static void main(String[] args) {
	// 	try {
	// 		new StudentListViewDAO();
	// 	} catch (ClassNotFoundException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	} catch (SQLException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
	// }

}
