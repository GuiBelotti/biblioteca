package Screens;

import features.loans.datasource.LoanDAO;
import features.loans.model.Loan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoanListScreen extends JFrame {
    private JTable loanTable;
    private DefaultTableModel tableModel;

    public LoanListScreen() {
        // Configurações da tela
        setTitle("Devolução de Livro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 550); // Tamanho fixo da janela
        setResizable(false); // Impedir redimensionamento da janela
        setLayout(null);

        // Título da tela
        JLabel titleLabel = new JLabel("Lista de empréstimos:");
        titleLabel.setBounds(20, 20, 200, 30);
        add(titleLabel);

        // Criação da tabela de empréstimos
        String[] columnNames = {"ISBN", "Nome", "Data de Empréstimo", "Data de Devolução"};
        tableModel = new DefaultTableModel(columnNames, 0);
        loanTable = new JTable(tableModel);

        // JScrollPane para a tabela
        JScrollPane scrollPane = new JScrollPane(loanTable);
        scrollPane.setBounds(10, 70, 760, 350); // Posição e tamanho do scrollPane
        add(scrollPane);

        // Botão de devolução
        JButton returnButton = new JButton("Devolver");
        returnButton.setBounds(340, 450, 100, 30); // Posição e tamanho do botão
        add(returnButton);

        returnButton.addActionListener(this::performReturnAction);

        // Preencher tabela com empréstimos
        updateTable(LoanDAO.getAllLoans());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performReturnAction(ActionEvent e) {
        int selectedRow = loanTable.getSelectedRow();
        if (selectedRow != -1) {
            int isbn = (int) loanTable.getValueAt(selectedRow, 0);
            LoanDAO.deleteLoan(isbn); // Remover o empréstimo do banco de dados

            // Remover linha da tabela
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo para devolução.",
                    "Nenhum empréstimo selecionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método para atualizar a tabela de empréstimos
    public void updateTable(List<Loan> loans) {
        tableModel.setRowCount(0); // Limpar tabela antes de adicionar novos dados

        for (Loan loan : loans) {
            Object[] rowData = {loan.getIsbn(), loan.getName(), loan.getLoanDate(), loan.getReturnDate()};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoanListScreen::new);
    }
}
