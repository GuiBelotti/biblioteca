package Screens;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;

import BookRelacioned.Book;
import BookRelacioned.BookDataBase;

public class AdmMenuScreen extends JFrame {

    private final BookDataBase bookDataBase;
    private static JTable table;
    private String[] columnNames;
    private List<Book> bookList;

    public AdmMenuScreen(String Name, String function, List<Book> bookList, BookDataBase bookDataBase) {

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 550);
        setLayout(null);

        this.bookList = bookList;
        this.bookDataBase = bookDataBase;

        columnNames = new String[]{"Título", "Autor", "Categoria", "ISBN"};

        // ------------- CRIAR OBJETOS ---------------
        JLabel userName = new JLabel("Olá " + Name);
        JLabel userCargo = new JLabel("Cargo: " + function);
        JButton exitButton = new JButton("Sair");
        JButton userButton = new JButton("Usuários");
        JTextField searchTextField = new JTextField();
        JComboBox<String> typeList = new JComboBox<>(columnNames);
        JButton searchButton = new JButton("Pesquisar");
        typeList.setPrototypeDisplayValue("Categoria");

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        DefaultCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                return false; // Impede a edição da célula
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultEditor(table.getColumnClass(i), nonEditableCellEditor);
        }
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {

                        String title = (String) table.getValueAt(selectedRow, 0);
                        String author = (String) table.getValueAt(selectedRow, 1);
                        String category = (String) table.getValueAt(selectedRow, 2);
                        int isbn = (int) table.getValueAt(selectedRow, 3);

                    }
                }
            }
        });
        for (Book book : bookList) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn()};
            model.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        JButton deleteButton = new JButton("Excluir");
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton devolutionButton = new JButton("Devolver");
        JButton loanButton = new JButton("Emprestar");
        JButton statusButton = new JButton("Status");
        //table.setEnabled(false);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    rendererComponent.setBackground(Color.BLUE);
                    rendererComponent.setForeground(Color.WHITE);
                } else {
                    rendererComponent.setBackground(Color.WHITE);
                    rendererComponent.setForeground(Color.BLACK);
                }
                return rendererComponent;
            }
        });


        // ------------- PERSONALIZAR OBJETOS ---------------
        userName.setFont(new Font("Arial", Font.BOLD, 30));
        userCargo.setFont(new Font("Arial", Font.BOLD, 20));
        TableColumn column1 = new TableColumn(0);
        TableColumn column2 = new TableColumn(1);
        TableColumn column3 = new TableColumn(2);
        TableColumn column4 = new TableColumn(3);
        column1.setHeaderValue("ISBN");
        column2.setHeaderValue("Título");
        column3.setHeaderValue("Categoria");
        column4.setHeaderValue("Autor");
        table.getColumnModel().getColumn(0).setPreferredWidth(220);
        table.getColumnModel().getColumn(1).setPreferredWidth(220);
        table.getColumnModel().getColumn(2).setPreferredWidth(220);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);

        // ------------- POSICIONAR OBJETOS ---------------
        userName.setBounds(20, 10, 300, 50);
        userCargo.setBounds(20, 40, 300, 50);
        exitButton.setBounds(660, 10, 100, 30);
        userButton.setBounds(550, 10, 100, 30);
        searchTextField.setBounds(20, 100, 246, 30);
        typeList.setBounds(268, 100, 246, 30);
        searchButton.setBounds(518, 100, 246, 30);
        scrollPane.setBounds(20, 150, 745, 300);
        addButton.setBounds(20, 470, 118, 30);
        deleteButton.setBounds(142, 470, 118, 30);
        editButton.setBounds(264, 470, 118, 30);
        loanButton.setBounds(386, 470, 118, 30);
        devolutionButton.setBounds(508, 470, 118, 30);
        statusButton.setBounds(630, 470, 118, 30);

        // ------------- ADICIONAR OBJETOS ---------------
        add(userName);
        add(userCargo);
        add(exitButton);
        add(userButton);
        add(searchTextField);
        add(typeList);
        add(searchButton);
        add(scrollPane);
        add(deleteButton);
        add(addButton);
        add(editButton);
        add(devolutionButton);
        add(loanButton);
        add(statusButton);

        setLocationRelativeTo(null);
        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new AddAndEditScreen("add", bookList, bookDataBase,null,null,null,null);
                List<Book> updatedBookList = bookDataBase.getBookDataBase();
                updateTable(updatedBookList);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String title = (String) table.getValueAt(selectedRow, 0);
                    String author = (String) table.getValueAt(selectedRow, 1);
                    String category = (String) table.getValueAt(selectedRow, 2);
                    int isbn = (int) table.getValueAt(selectedRow, 3);

                    new AddAndEditScreen("edit", bookList, bookDataBase, title, author, category, isbn);

                    List<Book> updatedBookList = bookDataBase.getBookDataBase();
                    updateTable(updatedBookList);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para editar.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    static void updateTable(List<Book> bookList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Book book : bookList) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn()};
            model.addRow(rowData);
        }
    }


}