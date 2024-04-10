package Screens;

import BookRelacioned.Book;
import BookRelacioned.BookDataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddAndEditScreen extends JFrame {

    public AddAndEditScreen(String function, List<Book> bookList, BookDataBase bookDataBase, String title, String author, String category, Integer isbn){

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));

        if ((function.equals("add"))) {
            setTitle("Adicionar livro");
        } else if(function.equals("edit")){
            setTitle("Editar livro");
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

        JLabel labelNewIsbn = new JLabel("Novo ISBN:"); // Label para o novo ISBN
        JTextField fieldNewIsbn = new JTextField(15); // Campo de texto para o novo ISBN

        if(function.equals("edit")){
            fieldTitulo.setText(title);
            fieldIsbn.setText(String.valueOf(isbn));
            fieldAuthor.setText(author);
            fieldCategory.setText(category);
            fieldQuantTotal.setText("teste");
        } else if(function.equals("add")){
            fieldTitulo.setText("");
            fieldIsbn.setText(String.valueOf(""));
            fieldAuthor.setText("");
            fieldCategory.setText("");
            fieldQuantTotal.setText("");
        }

        panel.add(labelTitulo);
        panel.add(fieldTitulo);

        panel.add(labelAuthor);
        panel.add(fieldAuthor);

        panel.add(labelCategory);
        panel.add(fieldCategory);

        panel.add(labelIsbn);
        panel.add(fieldIsbn);

        panel.add(labelNewIsbn);
        panel.add(fieldNewIsbn);

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

                    if (title.isEmpty() || author.isEmpty() || category.isEmpty() || isbnText.isEmpty() || quantTotalText.isEmpty()) {
                        JOptionPane.showMessageDialog(AddAndEditScreen.this, "Por favor, preencha todos os campos.", "Campos obrigatórios vazios", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            int isbn = Integer.parseInt(isbnText);
                            int quantTotal = Integer.parseInt(quantTotalText);

                            bookDataBase.addBook(title, author, category, isbn, quantTotal);
                            AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());

                            // Fechar a janela atual
                            dispose();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(AddAndEditScreen.this, "Por favor, insira um valor válido para ISBN e quantidade total.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        }
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
                    int oldIsbn = Integer.parseInt(fieldIsbn.getText());
                    int newIsbn = Integer.parseInt(fieldNewIsbn.getText()); // Obtendo o novo ISBN
                    int quantTotal = Integer.parseInt(fieldQuantTotal.getText());

                    bookDataBase.editBook(oldIsbn, title, author, category, newIsbn, quantTotal, quantTotal); // Passando o ISBN antigo e o novo
                    AdmMenuScreen.updateTable(bookDataBase.getBookDataBase());
                    dispose();
                }
            });

        }

    }

}
