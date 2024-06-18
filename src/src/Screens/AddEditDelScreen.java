package Screens;

import features.book.datasource.BookDAO;
import features.book.model.Book;

import javax.swing.*;
import java.awt.*;

public class AddEditDelScreen extends JFrame {
    private final JTextField titleField;
    private final JTextField authorField;
    private final JTextField categoryField;
    private final JTextField isbnField;
    private final JTextField quantityField;
    private final JTextField termField;

    public AddEditDelScreen(String function, String title, String author, String category, Integer isbn, Integer copias, Integer prazo) {

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelTitulo = new JLabel("Título:");
        titleField = new JTextField(35);
        JLabel labelIsbn = new JLabel("ISBN:");
        isbnField = new JTextField(15);
        JLabel labelAuthor = new JLabel("Autor:");
        authorField = new JTextField(30);
        JLabel labelCategory = new JLabel("Categoria:");
        categoryField = new JTextField(20);
        JLabel labelQuantTotal = new JLabel("Cópias:");
        quantityField = new JTextField(15);
        JLabel labelTerm = new JLabel("Prazo em dias:");
        termField = new JTextField(5);

        JLabel titleOfDeletedBook = new JLabel("  " + title);
        JLabel authorOfDeletedBook = new JLabel("  " + author);
        JLabel categoryOfDeletedBook = new JLabel("  " + category);
        JLabel isbnOfDeletedBook = new JLabel(String.valueOf(isbn));
        titleOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        authorOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        categoryOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        isbnOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        if (!(function.equals("delet"))) {
            panel.add(labelTitulo);
            panel.add(titleField);
            panel.add(labelAuthor);
            panel.add(authorField);
            panel.add(labelCategory);
            panel.add(categoryField);
            panel.add(labelIsbn);
            panel.add(isbnField);
            panel.add(labelTerm);
            panel.add(termField);
            panel.add(labelQuantTotal);
            panel.add(quantityField);
            JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton botaoSave = new JButton("Salvar");
            panelBotao.add(botaoSave);
            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(panel, BorderLayout.CENTER);
            contentPane.add(panelBotao, BorderLayout.SOUTH);

            setContentPane(contentPane);

            pack();

            if (function.equals("add")) {
                setTitle("Adicionar livro");
                titleField.setText("");
                isbnField.setText("");
                authorField.setText("");
                categoryField.setText("");
                termField.setText("");
                quantityField.setText("");

                botaoSave.addActionListener(event -> {
                    String title1 = titleField.getText();
                    String author1 = authorField.getText();
                    String category1 = categoryField.getText();
                    String isbnText = isbnField.getText();
                    String quantTotalText = quantityField.getText();
                    String term = termField.getText();

                    try {
                        int isbn1 = Integer.parseInt(isbnText);
                        int quantTotal = Integer.parseInt(quantTotalText);
                        int prazo1 = Integer.parseInt(term);

                        boolean isbnExists = false;
                        for (Book existingBook : BookDAO.getAllBooks()) {
                            if (existingBook.getIsbn() == isbn1) {
                                isbnExists = true;
                                break;
                            }
                        }

                        if (isbnExists) {
                            isbnField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                            JOptionPane.showMessageDialog(AddEditDelScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            BookDAO.addBook(title1, author1, category1, isbn1, quantTotal, prazo1);
                            AdmMenuScreen.updateTable(BookDAO.getAllBooks());
                            dispose();
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(AddEditDelScreen.this, "Por favor, insira um valor válido para ISBN, quantidade total e prazo.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                    }

                });
            }
            else if (function.equals("edit")) {
                setTitle("Editar livro");
                titleField.setText(title);
                isbnField.setText(String.valueOf(isbn));
                authorField.setText(author);
                categoryField.setText(category);
                quantityField.setText(String.valueOf(copias));
                termField.setText(String.valueOf(prazo));
                botaoSave.addActionListener(event -> {
                    String newTitle = titleField.getText();
                    String newAuthor = authorField.getText();
                    String newCategory = categoryField.getText();
                    int newIsbn = Integer.parseInt(isbnField.getText());
                    int newQuantityTotal = Integer.parseInt(quantityField.getText());
                    int newTerm = Integer.parseInt(termField.getText());

                    boolean isbnExists = false;
                    for (Book existingBook : BookDAO.getAllBooks()) {
                        if (existingBook.getIsbn() == newIsbn && existingBook.getIsbn() != isbn) {
                            isbnExists = true;
                            break;
                        }
                    }
                    if (isbnExists) {
                        isbnField.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                        JOptionPane.showMessageDialog(AddEditDelScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        BookDAO.updateBook(newTitle, newAuthor, newCategory, newIsbn, newQuantityTotal, newTerm);
                        AdmMenuScreen.updateTable(BookDAO.getAllBooks());
                        dispose();
                    }
                });

            }
        } else {

            setTitle("Deletar livro");
            panel.add(labelTitulo);
            panel.add(titleOfDeletedBook);
            panel.add(labelAuthor);
            panel.add(authorOfDeletedBook);
            panel.add(labelCategory);
            panel.add(categoryOfDeletedBook);
            panel.add(labelIsbn);
            panel.add(isbnOfDeletedBook);

            JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton botaoDelet = new JButton("Deletar");
            panelBotao.add(botaoDelet);
            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(panel, BorderLayout.CENTER);
            contentPane.add(panelBotao, BorderLayout.SOUTH);

            setContentPane(contentPane);

            pack();

            botaoDelet.addActionListener(event -> {
                BookDAO.deleteBook(isbn);
                AdmMenuScreen.updateTable(BookDAO.getAllBooks());
                dispose();
            });
        }
    }
}
