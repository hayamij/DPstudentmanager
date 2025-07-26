package vn.edu.giadinh.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import vn.edu.giadinh.business.StudentController;
import vn.edu.giadinh.business.StudentViewItem;
import vn.edu.giadinh.business.StudentViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentListViewUI extends JFrame {
    private JTextField txtSearch;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTable table;
    private DefaultTableModel model;
    
    // Controller for MVC pattern
    private StudentController controller;

    public StudentListViewUI() {
        super("Danh sách sinh viên");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        btnAdd = new JButton("Thêm");
        btnDelete = new JButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        // Panel top
        JPanel top = new JPanel(new BorderLayout(5,5));
        txtSearch = new JTextField();
        top.add(txtSearch, BorderLayout.CENTER);
        top.add(buttonPanel, BorderLayout.EAST);
        top.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(top, BorderLayout.NORTH);

        // Table
        String[] cols = {"STT","ID","Họ & tên","Ngày sinh","Chuyên ngành","Điểm TB","Xếp loại"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Setup button event
        setupEvents();
       
    }
    
    private void setupEvents() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddStudentDialog();
            }
        });
    }
    
    private void openAddStudentDialog() {
        if (controller != null) {
            AddStudentDialog dialog = new AddStudentDialog(this, controller);
            dialog.setVisible(true);
        } else {
            showError("Controller chưa được khởi tạo!");
        }
    }
    
    /**
     * Set controller for MVC pattern
     */
    public void setController(StudentController controller) {
        this.controller = controller;
    }
    
    /**
     * Refresh list through controller
     */
    public void refreshList() {
        if (controller != null) {
            controller.handleViewStudentList();
        }
    }

    /**
     * Display student list (called by controller)
     */
    public void showList(StudentViewModel studentViewModel) {
        model.setRowCount(0);
        for (StudentViewItem item : studentViewModel.studentList) {
            Object[] row = {
                item.stt,
                item.id,
                item.name,
                item.birthDate,
                item.major,
                item.gpa,
                item.academicRank
            };
            model.addRow(row);
        }
    }
    
    /**
     * Show error message to user
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show success message to user
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

   
}
