package Screens;

import features.book.datasource.BookDAO;
import features.loans.datasource.LoanDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanScreen extends JFrame {
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel isbnLabel;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel categoryLabel;
    private JLabel loanDateLabel;
    private JTextField loanDateField;
    private JLabel returnDateLabel;
    private JTextField returnDateField;
    private JButton confirmButton;

    public LoanScreen(String title, String author, String category, int isbn, int prazo) {
        setTitle("Empréstimo de Livro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLayout(null);

        isbnLabel = new JLabel("ISBN: " + isbn);
        titleLabel = new JLabel("Título: " + title);
        authorLabel = new JLabel("Autor: " + author);
        categoryLabel = new JLabel("Categoria: " + category);
        loanDateLabel = new JLabel("Data de Empréstimo:");
        loanDateField = new JTextField();
        loanDateField.setEditable(false);
        returnDateLabel = new JLabel("Data de Devolução:");
        returnDateField = new JTextField();
        returnDateField.setEditable(false);
        nameLabel = new JLabel("Emprestado para:");
        nameField = new JTextField();
        confirmButton = new JButton("Confirmar");

        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = currentDate.plusDays(prazo);

        loanDateField.setText(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        returnDateField.setText(returnDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        isbnLabel.setBounds(20, 20, 200, 20);
        titleLabel.setBounds(20, 50, 300, 20);
        authorLabel.setBounds(20, 80, 300, 20);
        categoryLabel.setBounds(20, 110, 300, 20);
        loanDateLabel.setBounds(20, 140, 150, 20);
        loanDateField.setBounds(180, 140, 150, 20);
        returnDateLabel.setBounds(20, 170, 150, 20);
        returnDateField.setBounds(180, 170, 150, 20);
        nameLabel.setBounds(20, 200, 150, 20);
        nameField.setBounds(180, 200, 150, 20);
        confirmButton.setBounds(120, 230, 150, 30);

        add(isbnLabel);
        add(titleLabel);
        add(authorLabel);
        add(categoryLabel);
        add(loanDateLabel);
        add(loanDateField);
        add(returnDateLabel);
        add(returnDateField);
        add(nameLabel);
        add(nameField);
        add(confirmButton);

        setLocationRelativeTo(null);
        setVisible(true);

        confirmButton.addActionListener(event -> {
            String borrowerName = nameField.getText().trim();
            if (!borrowerName.isEmpty()) {
                features.loans.datasource.LoanDAO.addLoan(isbn, borrowerName, currentDate, returnDate);
                BookDAO.desincrementBookCopies(isbn);

                JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
                dispose();
                AdmMenuScreen.updateTable(BookDAO.getAllBooks());
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha o nome do usuário.", "Campo Vazio", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}