package features.loans.datasource;

import features.loans.model.Loan;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Screens.AdmMenuScreen.bookTable;

public class LoanDAO {
    private static final String DATABASE_URL = "jdbc:sqlite:biblioteca.db";

    // Método para inicializar o banco de dados
    public static void createDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS loans ("
                + "isbn INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "loan_date DATE NOT NULL,"
                + "return_date DATE NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obter todos os empréstimos
    public static List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String selectSQL = "SELECT * FROM loans";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int loanIsbn = rs.getInt("isbn");
                String name = rs.getString("name");
                Date loanDate = rs.getDate("loan_date");
                Date returnDate = rs.getDate("return_date");

                Loan loan = new Loan(loanIsbn, name, loanDate.toLocalDate(), returnDate.toLocalDate());
                loans.add(loan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return loans;
    }

    // Método para adicionar um empréstimo ao banco de dados
    public static void addLoan(int isbn, String name, LocalDate loanDate, LocalDate returnDate) {
        String insertSQL = "INSERT INTO loans (isbn, name, loan_date, return_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, isbn);
            pstmt.setString(2, name);
            pstmt.setDate(3, Date.valueOf(loanDate));
            pstmt.setDate(4, Date.valueOf(returnDate));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para deletar um empréstimo do banco de dados
    public static void deleteLoan(int isbn) {
        String deleteSQL = "DELETE FROM loans WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para atualizar um empréstimo no banco de dados
    public static void updateLoan(int isbn, String name, LocalDate loanDate, LocalDate returnDate) {
        String updateSQL = "UPDATE loans SET name = ?, loan_date = ?, return_date = ? WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setDate(2, Date.valueOf(loanDate));
            pstmt.setDate(3, Date.valueOf(returnDate));
            pstmt.setInt(4, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para atualizar a tabela de empréstimos
    public static void updateTable() {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);

        List<Loan> updatedLoanList = getAllLoans();

        for (Loan loan : updatedLoanList) {
            Object[] rowData = {loan.getIsbn(), loan.getName(), loan.getLoanDate(), loan.getReturnDate()};
            model.addRow(rowData);
        }
    }

}