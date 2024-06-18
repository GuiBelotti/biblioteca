package Screens;

import features.book.datasource.BookDAO;
import features.loans.datasource.LoanDAO;
import features.loans.model.Loan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoanListScreen extends JFrame {
    private JTable loanTable;
    private DefaultTableModel tableModel;

    public LoanListScreen() {

        // Configurações da tela
        setTitle("Devolução de Livro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setResizable(false);
        setLayout(null);
        JLabel titleLabel = new JLabel("Lista de empréstimos:");
        titleLabel.setBounds(20, 20, 200, 30);
        add(titleLabel);

        // Criação da tabela de empréstimos
        String[] columnNames = {"ISBN", "Nome", "Data de Empréstimo", "Data de Devolução"};
        tableModel = new DefaultTableModel(columnNames, 0);
        loanTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(loanTable);
        scrollPane.setBounds(10, 70, 760, 350);
        add(scrollPane);

        JButton returnButton = new JButton("Devolver");
        returnButton.setBounds(340, 450, 100, 30);
        add(returnButton);

        returnButton.addActionListener(this::performReturnAction);

        updateTable(LoanDAO.getAllLoans());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performReturnAction(ActionEvent e) {
        int selectedRow = loanTable.getSelectedRow();
        if (selectedRow != -1) {
            int isbn = (int) loanTable.getValueAt(selectedRow, 0);
            BookDAO.incrementBookCopies(isbn);
            LoanDAO.deleteLoan(isbn);
            AdmMenuScreen.updateTable(BookDAO.getAllBooks());

            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo para devolução.",
                    "Nenhum empréstimo selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    // atualizar a tabela de empréstimos
    public void updateTable(List<Loan> loans) {
        tableModel.setRowCount(0);

        for (Loan loan : loans) {
            Object[] rowData = {loan.getIsbn(), loan.getName(), loan.getLoanDate(), loan.getReturnDate()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoanListScreen::new);
    }
}
