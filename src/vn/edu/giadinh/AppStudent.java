package vn.edu.giadinh;

import java.sql.SQLException;

import vn.edu.giadinh.business.AddStudentUseCase;
import vn.edu.giadinh.business.StudentListViewUseCase;
import vn.edu.giadinh.business.StudentController;
import vn.edu.giadinh.persistence.StudentListViewDAO;
import vn.edu.giadinh.presentation.StudentListViewUI;

public class AppStudent {

	public static void main(String[] args) {
		// Create View (UI)
		StudentListViewUI listViewUI = new StudentListViewUI();
		
		// Initialize components
		StudentListViewDAO listViewDAO = null;
		StudentListViewUseCase listViewUseCase = null;
		AddStudentUseCase addStudentUseCase = null;
		StudentController controller = null;
		
		try {
			// Create Model (Business Layer)
			listViewDAO = new StudentListViewDAO();
			listViewUseCase = new StudentListViewUseCase(listViewDAO);
			addStudentUseCase = new AddStudentUseCase(listViewDAO);
			
			// Create Controller (MVC Pattern)
			controller = new StudentController(listViewUI, listViewUseCase, addStudentUseCase);
			
			// Initialize and load data
			controller.initialize();
			
		} catch (ClassNotFoundException e) {
			System.err.println("Database driver error: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
		
		// Show UI
		listViewUI.setVisible(true);
	}

}
