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
import BookRelacioned.DatabaseManager;

public class AdmMenuScreen extends JFrame {
    private final BookDataBase bookDataBase;
    private static JTable table;
    private String[] columnNames;
    private List<Book> bookList;

    public AdmMenuScreen(String Name, String function, List<Book> bookList, BookDataBase bookDataBase) {

        //Configurações de tela
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 550);
        setLayout(null);

        this.bookList = bookList;
        this.bookDataBase = bookDataBase;

        //nomes para colunas
        columnNames = new String[]{"Título", "Autor", "Categoria", "ISBN", "Cópias", "Prazo"};

        //nomes para topicos pesquisa
        String[] boxNames = new String[]{"Título", "Autor", "Categoria", "ISBN"};

        // criando componentes da tela
        JLabel userName = new JLabel("Olá " + Name);
        JLabel userCargo = new JLabel("Cargo: " + function);
        JButton exitButton = new JButton("Sair");
        JButton userButton = new JButton("Usuários");
        JTextField searchTextField = new JTextField();
        JComboBox<String> typeList = new JComboBox<>(boxNames);
        JButton searchButton = new JButton("Pesquisar");
        typeList.setPrototypeDisplayValue("Categoria");

        //Criacao da tabela
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        DefaultCellEditor nonEditableCellEditor = new DefaultCellEditor(new JTextField()) {
            @Override
            // Impede a edição da célula
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            //aplica nao edicao em todas
            table.setDefaultEditor(table.getColumnClass(i), nonEditableCellEditor);
        }
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            //seleciona a tabela
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
            //roda a lista
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn(), book.getQuantTotal(), book.getPrazo()};
            model.addRow(rowData);
        }

        //cria botoes parte inferior
        JScrollPane scrollPane = new JScrollPane(table);
        JButton deleteButton = new JButton("Excluir");
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton devolutionButton = new JButton("Devolver");
        JButton loanButton = new JButton("Emprestar");
        JButton statusButton = new JButton("Status");

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Obtém a quantidade de cópias do livro na coluna "Cópias"
                int quantidade = (int) table.getValueAt(row, 4);
                int isbnBookDispo = (int) table.getValueAt(row, 3);
                boolean dispo = true;
                for (Book book : bookList) {
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
                    // Aplica a lógica do renderizador original se a quantidade de cópias não for zero
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


        // personaliza elementos
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
        table.getColumnModel().getColumn(0).setPreferredWidth(220);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(190);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);

        // posiciona elementos na tela
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

        // adiciona elementos na tela
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

        //funcao do botao add
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //abre nova tela
                new AddEditDelScreen("add", bookDataBase, null, null, null, null, null, null);
                List<Book> updatedBookList = bookDataBase.getBookDataBase();
                updateTable(updatedBookList);
            }
        });

        //funcao botao edit
        editButton.addActionListener(new ActionListener() {
            @Override
            //recebe valores da tabela e passa para a nova tela
            public void actionPerformed(ActionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String title = (String) table.getValueAt(selectedRow, 0);
                    String author = (String) table.getValueAt(selectedRow, 1);
                    String category = (String) table.getValueAt(selectedRow, 2);
                    int isbn = (int) table.getValueAt(selectedRow, 3);
                    int copias = (int) table.getValueAt(selectedRow, 4);
                    int term = (int) table.getValueAt(selectedRow, 5);

                    new AddEditDelScreen("edit", bookDataBase, title, author, category, isbn, copias, term); // Certifique-se de passar o valor de term corretamente

                    List<Book> updatedBookList = bookDataBase.getBookDataBase();
                    updateTable(updatedBookList);
                    //erro caso n selecione nenhuma linha
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para editar.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //botao para deletar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int selectedRow = table.getSelectedRow();
                //verifica se selecionou linha
                if (selectedRow != -1) {
                    String title = (String) table.getValueAt(selectedRow, 0);
                    String author = (String) table.getValueAt(selectedRow, 1);
                    String category = (String) table.getValueAt(selectedRow, 2);
                    int isbn = (int) table.getValueAt(selectedRow, 3);
                    //passa info para tela que ira deletar
                    new AddEditDelScreen("delet", bookDataBase, title, author, category, isbn, null, null);

                    List<Book> updatedBookList = bookDataBase.getBookDataBase();
                    updateTable(updatedBookList);
                    //erro caso n tenha selecionado
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para deletar.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //faz a pesquisa dos livros
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchTextField.getText();
                String searchType = (String) typeList.getSelectedItem();

                List<Book> updatedBookList;
                //verifica se o campo de pesquisa esta vazio para atualizar com todos os livros na tabela se nao ele pesquisa e atualiza a tabela com os livro encotrandos de acordo com a entrada
                if (searchTerm.isEmpty()) {
                    updatedBookList = bookDataBase.getBookDataBase();
                    updateTable(updatedBookList);
                } else {
                    List<Book> searchResults = bookDataBase.searchBooks(searchTerm, searchType);
                    updateTable(searchResults);
                }
            }
        });

        //botao status disponibilidade
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int selectedRow = table.getSelectedRow();
                //verifica se selecionou linha
                if (selectedRow != -1) {
                    int isbn = (int) table.getValueAt(selectedRow, 3);
                    Book selectedBook = null;
                    for (Book book : bookList) {
                        if (book.getIsbn() == isbn) {
                            selectedBook = book;
                            break;
                        }
                    }
                    if (selectedBook != null) {
                        //cria componentes da tela
                        JRadioButton availableRadioButton = new JRadioButton("Disponível");
                        JRadioButton unavailableRadioButton = new JRadioButton("Indisponível");
                        ButtonGroup buttonGroup = new ButtonGroup();
                        buttonGroup.add(availableRadioButton);
                        buttonGroup.add(unavailableRadioButton);

                        // Define a seleção inicial com base na disponibilidade atual do livro
                        if (selectedBook.isDispo()) {
                            availableRadioButton.setSelected(true);
                        } else {
                            unavailableRadioButton.setSelected(true);
                        }

                        //inicializa e add componentes
                        JPanel radioPanel = new JPanel();
                        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
                        radioPanel.add(availableRadioButton);
                        radioPanel.add(unavailableRadioButton);

                        //cria tela e passa a disponibilidade selecionada
                        int result = JOptionPane.showConfirmDialog(AdmMenuScreen.this, radioPanel, "Alterar status de disponibilidade", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            boolean availability = availableRadioButton.isSelected();
                            selectedBook.setDispo(availability);
                            updateTable(bookList);
                        }
                    }
                    //erro caso nao selecione nenhuma tela
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro na tabela para atualizar o status.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    public static void updateTable(List<Book> updatedBookList) {
        List<Book> books = DatabaseManager.getAllBooks();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Book book : books) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn(), book.getQuantTotal(), book.getPrazo()};
            model.addRow(rowData);
        }
    }

}