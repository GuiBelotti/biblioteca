package Screens;

import features.user.datasource.UserDAO;
import features.user.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.EventObject;
import java.util.List;

public class UserScreen extends JFrame {

    private static JTable userTable;
    private List<User> userList;

    public UserScreen() {
        // Configurações da tela
        setTitle("Usuários");
        setResizable(false);
        setSize(800, 550);
        setLayout(null);

        // Nomes das colunas
        String[] columnNames = new String[]{"Usuário", "Cargo", "Senha"};

        // Criando componentes da tela
        JLabel titleLabel = new JLabel("Lista de Usuários");
        JButton addButton = new JButton("Adicionar");
        JButton deleteButton = new JButton("Excluir");

        // Criação da tabela
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(model);

        DefaultCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        };

        for (int i = 0; i < userTable.getColumnCount(); i++) {
            userTable.setDefaultEditor(userTable.getColumnClass(i), nonEditableCellEditor);
        }

        JScrollPane scrollPane = new JScrollPane(userTable);

        // Posicionamento dos componentes
        titleLabel.setBounds(20, 20, 200, 30);
        addButton.setBounds(20, 450, 100, 30);
        deleteButton.setBounds(130, 450, 100, 30);
        scrollPane.setBounds(20, 70, 760, 350);

        // Adicionando componentes à tela
        add(titleLabel);
        add(addButton);
        add(deleteButton);
        add(scrollPane);

        // Carregando dados da base de dados
        updateTable(UserDAO.getAllUsers());

        // Configurando ação do botão "Adicionar"
        addButton.addActionListener(e -> {
            new AddUserScreen();
        });

        // Configurando ação do botão "Excluir"
        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = (String) userTable.getValueAt(selectedRow, 0);
                UserDAO.deleteUser(name);
                userList = UserDAO.getAllUsers();
                updateTable(userList);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela para excluir.",
                        "Nenhum usuário selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Exibindo a tela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void updateTable(List<User> userList) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Limpa todas as linhas da tabela

        // Adiciona os usuários à tabela
        for (User user : userList) {
            Object[] rowData = {user.getName(), user.getCargo(), user.getSenha()};
            model.addRow(rowData);
        }
    }
}