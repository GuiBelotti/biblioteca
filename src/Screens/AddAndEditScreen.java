package Screens;

import BookRelacioned.Book;
import BookRelacioned.BookDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAndEditScreen extends JFrame {

    public AddAndEditScreen(String function, BookDataBase bookDataBase, String title, String author, String category, Integer isbn, Integer copias){

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));

        if ((function.equals("add"))) {
            setTitle("Adicionar livro");
        } else if(function.equals("edit")){
            setTitle("Editar livro");
        } else if(function.equals("delet")){
            setTitle("Deletar livro");
        }

        JPanel panel = new JPanel(new GridLayout(8, 2)); // Adicionando uma linha para o novo ISBN
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel labelTitulo = new JLabel("Título:");
        JTextField fieldTitulo = new JTextField(35);
        JLabel labelIsbn = new JLabel("ISBN:");
        JTextField fieldIsbn = new JTextField(15);
        JLabel labelAuthor = new JLabel("Autor:");
        JTextField fieldAuthor = new JTextField(30);
        JLabel labelCategory = new JLabel("Categoria:");
        JTextField fieldCategory = new JTextField(20);
        JLabel labelQuantTotal = new JLabel("Cópias:");
        JTextField fieldQuantTotal = new JTextField(15);

        JLabel titleOfDeletedBook = new JLabel("  " + title);
        JLabel authorOfDeletedBook = new JLabel("  " + author);
        JLabel categoryOfDeletedBook = new JLabel("  " + category);
        JLabel isbnOfDeletedBook = new JLabel(String.valueOf("  " + isbn));


        titleOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        authorOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        categoryOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        isbnOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        if(function.equals("edit")){
            fieldTitulo.setText(title);
            fieldIsbn.setText(String.valueOf(isbn));
            fieldAuthor.setText(author);
            fieldCategory.setText(category);

            fieldQuantTotal.setText(String.valueOf(copias));
        } else if(function.equals("add")){
            fieldTitulo.setText("");
            fieldIsbn.setText(String.valueOf(""));
            fieldAuthor.setText("");
            fieldCategory.setText("");
            fieldQuantTotal.setText("");

        }

        if(!function.equals("delet")){
            panel.add(labelTitulo);
            panel.add(fieldTitulo);
            panel.add(labelAuthor);
            panel.add(fieldAuthor);
            panel.add(labelCategory);
            panel.add(fieldCategory);
            panel.add(labelIsbn);
            panel.add(fieldIsbn);
            panel.add(labelQuantTotal);
            panel.add(fieldQuantTotal);
            JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton botaoSave = new JButton("Salvar");
            panelBotao.add(botaoSave);
            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(panel, BorderLayout.CENTER);
            contentPane.add(panelBotao, BorderLayout.SOUTH);

            setContentPane(contentPane);

            pack();

            if(function.equals("add")){
                botaoSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String title = fieldTitulo.getText();
                        String author = fieldAuthor.getText();
                        String category = fieldCategory.getText();
                        String isbnText = fieldIsbn.getText();
                        String quantTotalText = fieldQuantTotal.getText();
                        try {
                            int isbn = Integer.parseInt(isbnText);
                            int quantTotal = Integer.parseInt(quantTotalText);

                            boolean isbnExists = false;
                            for (Book existingBook : bookDataBase.getBookDataBase()) {
                                if (existingBook.getIsbn() == isbn) {
                                    isbnExists = true;
                                    break;
                                }
                            }
                            if (isbnExists) {
                                fieldIsbn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                                JOptionPane.showMessageDialog(AddAndEditScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                            } else {

                                bookDataBase.addBook(title, author, category, isbn, quantTotal);
                                AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());

                                dispose();
                            }
                        } catch (NumberFormatException e) {

                            JOptionPane.showMessageDialog(AddAndEditScreen.this, "Por favor, insira um valor válido para ISBN e quantidade total.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            } else if(function.equals("edit")){
                botaoSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String title = fieldTitulo.getText();
                        String author = fieldAuthor.getText();
                        String category = fieldCategory.getText();
                        int newIsbn = Integer.parseInt(fieldIsbn.getText());
                        int quantTotal = Integer.parseInt(fieldQuantTotal.getText());

                        boolean isbnExists = false;
                        for (Book existingBook : bookDataBase.getBookDataBase()) {
                            if (existingBook.getIsbn() == newIsbn && existingBook.getIsbn() != isbn) {
                                isbnExists = true;
                                break;
                            }
                        }
                        if (isbnExists) {
                            fieldIsbn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                            JOptionPane.showMessageDialog(AddAndEditScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            bookDataBase.editBook(isbn, title, author, category, newIsbn, quantTotal);
                            AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                            dispose();
                        }
                    }
                });

            }
        }

        if(function.equals("delet")){
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

            botaoDelet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    bookDataBase.deletedBook(isbn);
                    AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                    dispose();
                }
            });

        }



        }





    }

