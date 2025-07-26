package vn.edu.giadinh.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.edu.giadinh.business.EconomicsStudent;
import vn.edu.giadinh.business.SoftwareStudent;
import vn.edu.giadinh.business.Student;
import vn.edu.giadinh.business.StudentController;

public class AddStudentDialog extends JDialog {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBirthday;
    private JComboBox<String> cmbMajor;
    private JTextField txtJavaScore;
    private JTextField txtHtmlScore;
    private JTextField txtCssScore;
    private JTextField txtMarketing;
    private JTextField txtSalesScore;
    private JPanel scorePanel;
    private JButton btnSave;
    private JButton btnCancel;
    
    private StudentController controller;
    // private StudentListViewUI parentUI;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public AddStudentDialog(StudentListViewUI parent, StudentController controller) {
        super(parent, "Thêm sinh viên mới", true);
        // this.parentUI = parent;
        this.controller = controller;
        
        initComponents();
        setupLayout();
        setupEvents();
        
        setSize(400, 350);
        setLocationRelativeTo(parent);
    }
    
    private void initComponents() {
        txtId = new JTextField(15);
        txtName = new JTextField(15);
        txtBirthday = new JTextField(15);
        txtBirthday.setToolTipText("Định dạng: dd/MM/yyyy");
        
        cmbMajor = new JComboBox<>(new String[]{"Software", "Economics"});
        
        txtJavaScore = new JTextField(10);
        txtHtmlScore = new JTextField(10);
        txtCssScore = new JTextField(10);
        txtMarketing = new JTextField(10);
        txtSalesScore = new JTextField(10);
        
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Main form panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Basic info
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Mã SV:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtId, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtName, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtBirthday, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Chuyên ngành:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(cmbMajor, gbc);
        
        // Score panel
        scorePanel = new JPanel(new GridBagLayout());
        scorePanel.setBorder(BorderFactory.createTitledBorder("Điểm số"));
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(scorePanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Initialize score panel for Software (default)
        updateScorePanel();
    }
    
    private void updateScorePanel() {
        scorePanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        String selectedMajor = (String) cmbMajor.getSelectedItem();
        
        if ("Software".equals(selectedMajor)) {
            gbc.gridx = 0; gbc.gridy = 0;
            scorePanel.add(new JLabel("Điểm Java:"), gbc);
            gbc.gridx = 1;
            scorePanel.add(txtJavaScore, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            scorePanel.add(new JLabel("Điểm HTML:"), gbc);
            gbc.gridx = 1;
            scorePanel.add(txtHtmlScore, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2;
            scorePanel.add(new JLabel("Điểm CSS:"), gbc);
            gbc.gridx = 1;
            scorePanel.add(txtCssScore, gbc);
        } else {
            gbc.gridx = 0; gbc.gridy = 0;
            scorePanel.add(new JLabel("Điểm Marketing:"), gbc);
            gbc.gridx = 1;
            scorePanel.add(txtMarketing, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            scorePanel.add(new JLabel("Điểm Sales:"), gbc);
            gbc.gridx = 1;
            scorePanel.add(txtSalesScore, gbc);
        }
        
        scorePanel.revalidate();
        scorePanel.repaint();
    }
    
    private void setupEvents() {
        cmbMajor.addActionListener(e -> updateScorePanel());
        
        btnCancel.addActionListener(e -> dispose());
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
    }
    
    private void saveStudent() {
        try {
            // Validate input
            if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || 
                txtBirthday.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            Date birthDate = dateFormat.parse(txtBirthday.getText().trim());
            String major = (String) cmbMajor.getSelectedItem();
            
            Student student = null;
            
            if ("Software".equals(major)) {
                double javaScore = Double.parseDouble(txtJavaScore.getText().trim());
                double htmlScore = Double.parseDouble(txtHtmlScore.getText().trim());
                double cssScore = Double.parseDouble(txtCssScore.getText().trim());
                
                student = new SoftwareStudent(id, name, birthDate, javaScore, htmlScore, cssScore);
            } else {
                double marketing = Double.parseDouble(txtMarketing.getText().trim());
                double salesScore = Double.parseDouble(txtSalesScore.getText().trim());
                
                student = new EconomicsStudent(id, name, birthDate, marketing, salesScore);
            }
            
            // Save student through controller
            controller.handleAddStudent(student);
            dispose();
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không đúng! (dd/MM/yyyy)", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm số phải là số!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
