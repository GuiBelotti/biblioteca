package Screens;

import features.book.datasource.BookDAO;
import features.book.model.Book;
import features.user.datasource.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventObject;
import java.util.List;

public class AdmMenuScreen extends JFrame {
    public static JTable bookTable;
    private List<Book> bookList;

    public AdmMenuScreen(String Name, String function, List<Book> bookList, String usuario) {
        this.bookList = bookList;

        // Configurações de tela
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 550);
        setLayout(null);

        // Nomes para colunas
        String[] columnNames = new String[]{"Título", "Autor", "Categoria", "ISBN", "Cópias", "Prazo"};

        // Nomes para tópicos pesquisa
        String[] boxNames = new String[]{"Título", "Autor", "Categoria", "ISBN"};

        // Criando componentes da tela
        JLabel userName = new JLabel("Olá " + Name);
        JLabel userCargo = new JLabel("Cargo: " + function);
        JButton exitButton = new JButton("Sair");
        JButton userButton = new JButton("Usuários");
        JTextField searchTextField = new JTextField();
        JComboBox<String> typeList = new JComboBox<>(boxNames);
        JButton searchButton = new JButton("Pesquisar");
        typeList.setPrototypeDisplayValue("Categoria");

        // Criação da tabela
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(model);
        DefaultCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        };

        for (int i = 0; i < bookTable.getColumnCount(); i++) {
            bookTable.setDefaultEditor(bookTable.getColumnClass(i), nonEditableCellEditor);
        }

        JScrollPane scrollPane = new JScrollPane(bookTable);
        JButton deleteButton = new JButton("Excluir");
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton devolutionButton = new JButton("Devolver");
        JButton loanButton = new JButton("Emprestar");
        JButton statusButton = new JButton("Status");

        bookTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                int quantidade = (int) table.getValueAt(row, 4);
                int isbnBookDispo = (int) table.getValueAt(row, 3);
                boolean dispo = true;
                for (Book book : AdmMenuScreen.this.bookList) {
                    if (book.getIsbn() == isbnBookDispo) {
                        if (!book.isDispo()) {
                            dispo = false;
                        }
                    }
                }
                if ((quantidade == 0) || (!dispo)) {
                    rendererComponent.setBackground(Color.RED);
                    rendererComponent.setForeground(isSelected ? Color.WHITE : Color.BLACK);
                } else {
                    if (isSelected) {
                        rendererComponent.setBackground(Color.BLUE);
                        rendererComponent.setForeground(Color.WHITE);
                    } else {
                        rendererComponent.setBackground(Color.WHITE);
                        rendererComponent.setForeground(Color.BLACK);
                    }
                }

                return rendererComponent;
            }
        });

        userName.setFont(new Font("Arial", Font.BOLD, 30));
        userCargo.setFont(new Font("Arial", Font.BOLD, 20));
        TableColumn column1 = new TableColumn(0);
        TableColumn column2 = new TableColumn(1);
        TableColumn column3 = new TableColumn(2);
        TableColumn column4 = new TableColumn(3);
        TableColumn column5 = new TableColumn(4);
        column1.setHeaderValue("ISBN");
        column2.setHeaderValue("Título");
        column3.setHeaderValue("Categoria");
        column4.setHeaderValue("Autor");
        column5.setHeaderValue("Cópias");
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(190);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        bookTable.getColumnModel().getColumn(4).setPreferredWidth(50);

        userName.setBounds(20, 10, 300, 50);
        userCargo.setBounds(20, 40, 300, 50);
        exitButton.setBounds(660, 10, 100, 30);
        userButton.setBounds(550, 10, 100, 30);
        searchTextField.setBounds(20, 100, 246, 30);
        typeList.setBounds(268, 100, 246, 30);
        searchButton.setBounds(518, 100, 246, 30);
        scrollPane.setBounds(20, 150, 745, 300);

        addButton.setBounds(20, 470, 100, 30);
        deleteButton.setBounds(149, 470, 100, 30);
        editButton.setBounds(278, 470, 100, 30);
        loanButton.setBounds(407, 470, 100, 30);
        devolutionButton.setBounds(536, 470, 100, 30);
        statusButton.setBounds(665, 470, 100, 30);

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

        if ("admin".equalsIgnoreCase(UserDAO.getUserCargo(usuario))){
            userName.setVisible(true);
            userCargo.setVisible(true);
            exitButton.setVisible(true);
            userButton.setVisible(true);
            searchButton.setVisible(true);
            searchTextField.setVisible(true);
            typeList.setVisible(true);
            scrollPane.setVisible(true);
            deleteButton.setVisible(true);
            addButton.setVisible(true);
            editButton.setVisible(true);
            devolutionButton.setVisible(true);
            loanButton.setVisible(true);
            statusButton.setVisible(true);
        }
        else {
            userName.setVisible(true);
            userCargo.setVisible(true);
            exitButton.setVisible(true);
            userButton.setVisible(false);
            searchButton.setVisible(true);
            searchTextField.setVisible(true);
            typeList.setVisible(true);
            scrollPane.setVisible(true);
            deleteButton.setVisible(false);
            addButton.setVisible(false);
            editButton.setVisible(false);
            devolutionButton.setVisible(true);
            loanButton.setVisible(true);
            statusButton.setVisible(false);

            loanButton.setBounds(20, 470, 100, 30);
            devolutionButton.setBounds(149, 470, 100, 30);
        }


        setLocationRelativeTo(null);
        setVisible(true);

        userButton.addActionListener(event -> new UserScreen());

        BookDAO.updateTable();

        addButton.addActionListener(event -> {
            new AddEditDelScreen("add", null, null, null, null, null, null);
            List<Book> updatedBookList = BookDAO.getAllBooks();
            AdmMenuScreen.updateTable(updatedBookList);
        });

        editButton.addActionListener(event -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                final String title = (String) bookTable.getValueAt(selectedRow, 0);
                final String author = (String) bookTable.getValueAt(selectedRow, 1);
                final String category = (String) bookTable.getValueAt(selectedRow, 2);
                final int isbn = (int) bookTable.getValueAt(selectedRow, 3);
                final int copias = (int) bookTable.getValueAt(selectedRow, 4);
                final int term = (int) bookTable.getValueAt(selectedRow, 5);

                AddEditDelScreen editScreen = new AddEditDelScreen("edit", title, author, category, isbn, copias, term);
                editScreen.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para editar.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        deleteButton.addActionListener(event -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                final String title = (String) bookTable.getValueAt(selectedRow, 0);
                final String author = (String) bookTable.getValueAt(selectedRow, 1);
                final String category = (String) bookTable.getValueAt(selectedRow, 2);
                final int isbn = (int) bookTable.getValueAt(selectedRow, 3);
                final int quantity = (int) bookTable.getValueAt(selectedRow, 4);
                final int term = (int) bookTable.getValueAt(selectedRow, 5);

                new AddEditDelScreen("delet", title, author, category, isbn, quantity, term);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para excluir.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        loanButton.addActionListener(event -> {
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                final String title = (String) bookTable.getValueAt(selectedRow, 0);
                final String author = (String) bookTable.getValueAt(selectedRow, 1);
                final String category = (String) bookTable.getValueAt(selectedRow, 2);
                final int isbn = (int) bookTable.getValueAt(selectedRow, 3);
                final int prazo = (int) bookTable.getValueAt(selectedRow, 5);

                new LoanScreen(title, author, category, isbn, prazo);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para fazer o empréstimo.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        devolutionButton.addActionListener(event -> {
            LoanListScreen loanListScreen = new LoanListScreen();
            loanListScreen.setVisible(true);
        });

        statusButton.addActionListener(this::actionPerformed);

        searchButton.addActionListener((ActionEvent e) -> {
            String searchText = searchTextField.getText().trim();
            String searchType = (String) typeList.getSelectedItem();
            if (searchType != null) {
                List<Book> results = BookDAO.searchBooks(searchText, searchType.toLowerCase());
                updateTable(results);
            }
            else {
                updateTable(BookDAO.getAllBooks());
            }
        });

        exitButton.addActionListener((ActionEvent e) -> {
            dispose();
            new LoginScreen(null);
        });
    }

    private Book getSelectedBook(int selectedRow) {
        int isbn = (int) bookTable.getValueAt(selectedRow, 3);
        for (Book book : bookList) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        return null;
    }

    public static void updateTable(List<Book> updatedBookList) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);

        for (Book book : updatedBookList) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn(), book.getQuantTotal(), book.getPrazo()};
            model.addRow(rowData);
        }
    }

    private void actionPerformed(ActionEvent event) {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            Book selectedBook = getSelectedBook(selectedRow);
            if (selectedBook != null) {
                JRadioButton availableRadioButton = new JRadioButton("Disponível");
                JRadioButton unavailableRadioButton = new JRadioButton("Indisponível");
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(availableRadioButton);
                buttonGroup.add(unavailableRadioButton);

                if (selectedBook.isDispo()) {
                    availableRadioButton.setSelected(true);
                } else {
                    unavailableRadioButton.setSelected(true);
                }

                JPanel radioPanel = new JPanel();
                radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
                radioPanel.add(availableRadioButton);
                radioPanel.add(unavailableRadioButton);

                int result = JOptionPane.showConfirmDialog(AdmMenuScreen.this, radioPanel, "Alterar status de disponibilidade", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    boolean availability = availableRadioButton.isSelected();
                    selectedBook.setDispo(availability);

                    BookDAO.updateBookAvailability(selectedBook.getIsbn(), availability);
                    bookList = BookDAO.getAllBooks();
                    updateTable(bookList);
                }
            } else {
                JOptionPane.showMessageDialog(AdmMenuScreen.this, "Erro: Livro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(AdmMenuScreen.this, "Selecione um livro na tabela para atualizar o status.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }


}