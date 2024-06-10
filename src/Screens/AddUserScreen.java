package Screens;

import features.user.datasource.UserDAO;

import javax.swing.*;
import java.awt.*;

import static Screens.UserScreen.updateTable;

public class AddUserScreen extends JFrame {

    private final JTextField userField;
    private final JPasswordField passwordField;

    public AddUserScreen() {
        setTitle("Adicionar Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Configurações do layout
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel userLabel = new JLabel("Usuário:");
        userField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);

        JLabel roleLabel = new JLabel("Cargo:");
        JRadioButton adminRadioButton = new JRadioButton("Administrador");
        adminRadioButton.setActionCommand("admin");
        JRadioButton employeeRadioButton = new JRadioButton("Funcionário");
        employeeRadioButton.setActionCommand("funcionario");

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(employeeRadioButton);

        JButton saveButton = new JButton("Salvar");

        // Adicionando componentes ao painel
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(adminRadioButton);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(employeeRadioButton);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(saveButton);

        add(panel);

        // Ação do botão "Salvar"
        saveButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleGroup.getSelection().getActionCommand();

            if (username.isEmpty() || password.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                UserDAO.addUser(username, password, role);
                JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                updateTable(UserDAO.getAllUsers()); // Atualiza a tabela na UserScreen
                dispose();
            }
        });

        setVisible(true);
    }
}