package Screens;
import BookRelacioned.Book;
import BookRelacioned.BookDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditDelScreen extends JFrame {

    public AddEditDelScreen(String function, BookDataBase bookDataBase, String title, String author, String category, Integer isbn, Integer copias, Integer prazo) {

        // configuro a tela
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));

        //configuro e crio painel para livros
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //crio componentes usados nas telas
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
        JLabel labelTerm = new JLabel("Prazo em dias:");
        JTextField fieldTerm = new JTextField(5);

        //crio componentes e estilizo para mostrar caso for deletar o livro

        JLabel titleOfDeletedBook = new JLabel("  " + title);
        JLabel authorOfDeletedBook = new JLabel("  " + author);
        JLabel categoryOfDeletedBook = new JLabel("  " + category);
        JLabel isbnOfDeletedBook = new JLabel(String.valueOf("  " + isbn));
        titleOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        authorOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        categoryOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        isbnOfDeletedBook.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Adicionar na tela elementos especificos quando nao for deletar livro
        if (!(function.equals("delet"))) {
            panel.add(labelTitulo);
            panel.add(fieldTitulo);
            panel.add(labelAuthor);
            panel.add(fieldAuthor);
            panel.add(labelCategory);
            panel.add(fieldCategory);
            panel.add(labelIsbn);
            panel.add(fieldIsbn);
            panel.add(labelTerm);
            panel.add(fieldTerm);
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

            //Adicionar na tela elementos especificos quando for igual da funcao add
            if (function.equals("add")) {
                setTitle("Adicionar livro");
                fieldTitulo.setText("");
                fieldIsbn.setText("");
                fieldAuthor.setText("");
                fieldCategory.setText("");
                fieldTerm.setText("");
                fieldQuantTotal.setText("");

                //Ações ao clica no botao salvar
                botaoSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String title = fieldTitulo.getText();
                        String author = fieldAuthor.getText();
                        String category = fieldCategory.getText();
                        String isbnText = fieldIsbn.getText();
                        String quantTotalText = fieldQuantTotal.getText();
                        String term = fieldTerm.getText();

                        try {
                            int isbn = Integer.parseInt(isbnText);
                            int quantTotal = Integer.parseInt(quantTotalText);
                            int prazo = Integer.parseInt(term); // Adicione esta linha para obter o valor do prazo

                            boolean isbnExists = false;
                            //verifica se isbn existe
                            for (Book existingBook : bookDataBase.getBookDataBase()) {
                                if (existingBook.getIsbn() == isbn) {
                                    isbnExists = true;
                                    break;
                                }
                            }

                            //Mensagem de aviso que ja existe isbn
                            if (isbnExists) {
                                fieldIsbn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                                JOptionPane.showMessageDialog(AddEditDelScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                            } else {

                                // Aqui estamos passando o valor do prazo corretamente
                                bookDataBase.addBook(title, author, category, isbn, quantTotal, prazo, true);
                                AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                                dispose();
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(AddEditDelScreen.this, "Por favor, insira um valor válido para ISBN, quantidade total e prazo.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                });
                //se for editar
            } else if (function.equals("edit")) {
                setTitle("Editar livro");
                fieldTitulo.setText(title);
                fieldIsbn.setText(String.valueOf(isbn));
                fieldAuthor.setText(author);
                fieldCategory.setText(category);
                fieldQuantTotal.setText(String.valueOf(copias));
                fieldTerm.setText(String.valueOf(prazo));
                botaoSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //passa os valores atuais do livro para modificações
                        String title = fieldTitulo.getText();
                        String author = fieldAuthor.getText();
                        String category = fieldCategory.getText();
                        int newIsbn = Integer.parseInt(fieldIsbn.getText());
                        int quantTotal = Integer.parseInt(fieldQuantTotal.getText());
                        int term = Integer.parseInt(fieldTerm.getText());

                        //verifica o isbn novo
                        boolean isbnExists = false;
                        for (Book existingBook : bookDataBase.getBookDataBase()) {
                            if (existingBook.getIsbn() == newIsbn && existingBook.getIsbn() != isbn) {
                                isbnExists = true;
                                break;
                            }
                        }
                        //caso erro
                        if (isbnExists) {
                            fieldIsbn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                            JOptionPane.showMessageDialog(AddEditDelScreen.this, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            //conclui a edicao das informações
                            bookDataBase.editBook(isbn, title, author, category, newIsbn, quantTotal, term);
                            AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                            dispose();
                        }
                    }
                });

            }
        } else  {

            //deleta livro
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

            botaoDelet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    //deleta livro pelo isbn
                    bookDataBase.deletedBook(isbn);
                    AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                    dispose();
                }
            });

        }

    }
    }

