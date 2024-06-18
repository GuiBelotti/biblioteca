package Screens;

import features.user.datasource.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditUserScreen extends JFrame {

    private JTextField nameField;
    private JTextField cargoField;
    private JPasswordField senhaField;

    private int rowIndex;

    public EditUserScreen(String name, String cargo, String senha, int rowIndex) {
        this.rowIndex = rowIndex;

        setTitle("Editar Usuário");
        setSize(400, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(50, 30, 80, 30);
        add(nameLabel);

        nameField = new JTextField(name);
        nameField.setBounds(140, 30, 200, 30);
        add(nameField);

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(50, 70, 80, 30);
        add(cargoLabel);

        cargoField = new JTextField(cargo);
        cargoField.setBounds(140, 70, 200, 30);
        add(cargoField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 110, 80, 30);
        add(senhaLabel);

        senhaField = new JPasswordField(senha);
        senhaField.setBounds(140, 110, 200, 30);
        add(senhaField);

        JButton saveButton = new JButton("Salvar");
        saveButton.setBounds(120, 160, 100, 30);
        add(saveButton);

        saveButton.addActionListener(this::performSaveAction);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performSaveAction(ActionEvent e) {
        String newName = nameField.getText();
        String newCargo = cargoField.getText();
        String newSenha = String.valueOf(senhaField.getPassword());

        UserDAO.updateUser(newName, newCargo, newSenha);

        UserScreen.updateTableRow(newName, newCargo, newSenha, rowIndex);

        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");

        dispose();
    }
}