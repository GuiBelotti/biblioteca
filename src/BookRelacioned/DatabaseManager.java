package BookRelacioned;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:bookDataBase.db";

    public static Connection openDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createDatabase() {
        // Conectar ao banco de dados
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Conexão estabelecida com o banco de dados SQLite.");

                // Criar a estrutura da tabela
                try (Statement statement = conn.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS books (" +
                            "title TEXT NOT NULL," +
                            "author TEXT NOT NULL," +
                            "category TEXT NOT NULL," +
                            "isbn INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "quantityTotal INTEGER NOT NULL," +
                            "quantityLend INTEGER NOT NULL," +
                            "available INTEGER NOT NULL" +
                            ");";
                    statement.execute(sql);
                    System.out.println("Tabela 'books' criada no banco de dados.");
                } catch (SQLException e) {
                    System.err.println("Erro ao criar a tabela: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static void addBook(String title, String author, String category, int isbn, int quantTotal, int available) {
        try (Connection conn = openDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (title, author, category, isbn, quantityTotal, quantityLend, available) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setInt(4, isbn);
            pstmt.setInt(5, quantTotal);
            pstmt.setInt(6, 0); // quantityLend inicialmente 0
            pstmt.setInt(7, available);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro adicionado ao banco de dados.");
            } else {
                System.out.println("Nenhum livro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livro ao banco de dados: " + e.getMessage());
        }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = openDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                int isbn = resultSet.getInt("isbn");
                int quantityTotal = resultSet.getInt("quantityTotal");
                int quantityLend = resultSet.getInt("quantityLend");
                int available = resultSet.getInt("available");

                Book book = new Book(title, author, category, isbn, quantityTotal, quantityLend, available);
                books.add(book);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao recuperar livros do banco de dados: " + e.getMessage());
        }

        return books;
    }

    public static void printAllBooks() {
        List<Book> books = getAllBooks();
        System.out.println("Lista de Livros:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor() + " - " + book.getCategory());
        }
    }

}
