package src.features.book.datasource;
import features.book.model.Book;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static src.Screens.AdmMenuScreen.bookTable;

public class BookDAO {
    private static final String DATABASE_URL = "jdbc:sqlite:biblioteca.db";

    // Inicia BD
    public static void createDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "author TEXT NOT NULL,"
                + "category TEXT NOT NULL,"
                + "isbn INTEGER NOT NULL,"
                + "quantTotal INTEGER NOT NULL,"
                + "prazo INTEGER NOT NULL,"
                + "dispo BOOLEAN NOT NULL DEFAULT TRUE"
                + ");";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Pega todos livros do BD
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String selectSQL = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("isbn"),
                        rs.getInt("quantTotal"),
                        rs.getInt("prazo"),
                        rs.getBoolean("dispo")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    // Adicionar livro no BD
    public static void addBook(String title, String author, String category, int isbn, int quantTotal, int prazo) {
        String insertSQL = "INSERT INTO books (title, author, category, isbn, quantTotal, prazo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setInt(4, isbn);
            pstmt.setInt(5, quantTotal);
            pstmt.setInt(6, prazo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletar livro do BD
    public static void deleteBook(int isbn) {
        String deleteSQL = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Atualizar disponibilidade do livro no BD
    public static void updateBookAvailability(int isbn, boolean availability) {
        String updateSQL = "UPDATE books SET dispo = ? WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setBoolean(1, availability);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Pesquisar livro no BD
    public static List<Book> searchBooks(String searchText, String searchType) {
        List<Book> results = new ArrayList<>();
        String selectSQL = "SELECT * FROM books WHERE ";

        switch (searchType) {
            case "título":
                selectSQL += "title LIKE ?";
                break;
            case "autor":
                selectSQL += "author LIKE ?";
                break;
            case "categoria":
                selectSQL += "category LIKE ?";
                break;
            case "isbn":
                selectSQL += "isbn = ?";
                break;
        }

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            if (searchType.equals("isbn")) {
                pstmt.setInt(1, Integer.parseInt(searchText));
            } else {
                pstmt.setString(1, "%" + searchText + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("isbn"),
                            rs.getInt("quantTotal"),
                            rs.getInt("prazo"),
                            rs.getBoolean("dispo")
                    );
                    results.add(book);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    // Atualizar o livro no BD
    public static void updateBook(String title, String author, String category, int isbn, int quantityTotal, int term) {
        String updateSQL = "UPDATE books SET title = ?, author = ?, category = ?, quantTotal = ?, prazo = ? WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setInt(4, quantityTotal);
            pstmt.setInt(5, term);
            pstmt.setInt(6, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Adicionar uma copia pela devolucao
    public static void incrementBookCopies(int isbn) {
        String updateSQL = "UPDATE books SET quantTotal = quantTotal + 1 WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Retirar uma copia pela emprestimo
    public static void desincrementBookCopies(int isbn) {
        String updateSQL = "UPDATE books SET quantTotal = quantTotal - 1 WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Atualizar tabela de livros
    public static void updateTable() {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);

        List<Book> updatedBookList = BookDAO.getAllBooks();

        for (Book book : updatedBookList) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getCategory(), book.getIsbn(), book.getQuantTotal(), book.getPrazo()};
            model.addRow(rowData);
        }
    }
}