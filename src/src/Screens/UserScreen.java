package Screens;

import features.user.datasource.UserDAO;
import features.user.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserScreen extends JFrame {

    private static JTable userTable;

    public UserScreen() {
        // Configurações da tela
        setTitle("Usuários");
        setResizable(false);
        setSize(800, 550);
        setLayout(null);

        String[] columnNames = new String[]{"Usuário", "Cargo", "Senha"};

        JLabel titleLabel = new JLabel("Lista de Usuários");
        JButton addButton = new JButton("Adicionar");
        JButton deleteButton = new JButton("Excluir");
        JButton editButton = new JButton("Editar");

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(userTable);

        titleLabel.setBounds(20, 20, 200, 30);
        addButton.setBounds(20, 450, 100, 30);
        deleteButton.setBounds(130, 450, 100, 30);
        editButton.setBounds(240, 450, 100, 30);
        scrollPane.setBounds(20, 70, 760, 350);

        add(titleLabel);
        add(addButton);
        add(deleteButton);
        add(editButton);
        add(scrollPane);

        updateTable(UserDAO.getAllUsers());

        addButton.addActionListener(e -> new AddUserScreen());

        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) userTable.getValueAt(selectedRow, 0);
                UserDAO.deleteUser(name);
                updateTable(UserDAO.getAllUsers());
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela para excluir.",
                        "Nenhum usuário selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        editButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) userTable.getValueAt(selectedRow, 0);
                String cargo = (String) userTable.getValueAt(selectedRow, 1);
                String senha = (String) userTable.getValueAt(selectedRow, 2);
                new EditUserScreen(name, cargo, senha, selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela para editar.",
                        "Nenhum usuário selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void updateTable(List<User> userList) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        for (User user : userList) {
            Object[] rowData = {user.getName(), user.getCargo(), user.getSenha()};
            model.addRow(rowData);
        }
    }

    public static void updateTableRow(String name, String cargo, String senha, int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setValueAt(name, rowIndex, 0);
        model.setValueAt(cargo, rowIndex, 1);
        model.setValueAt(senha, rowIndex, 2);
    }
}