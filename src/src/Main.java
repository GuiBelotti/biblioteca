package src;
import src.features.book.datasource.BookDAO;
import src.features.loans.datasource.LoanDAO;
import src.features.user.datasource.UserDAO;
import src.Screens.LoginScreen;
import src.Screens.UserScreen;

public class Main {
    public static void main(String[] args) {

        BookDAO bookDataBase = new BookDAO();
        UserDAO userDataBase = new UserDAO();
        LoanDAO loanDataBase = new LoanDAO();
        BookDAO.createDatabase();
        UserDAO.createDatabase();
        LoanDAO.createDatabase();

//        BookDAO.deleteBook(1);
//        BookDAO.deleteBook(2);
//        BookDAO.addBook("Biologia Celular", "Bruce Alberts", "Biologia", 1, 3, 3);
//        BookDAO.addBook("Campbell Biologia", "Jane B. Reece", "Biologia", 2, 5 ,3);
//        BookDAO.addBook("Sapiens: Uma Breve História da Humanidade", "Yuval Noah Harari", "História", 3, 10, 8);
//        BookDAO.addBook("O Gene: Uma História Íntima", "Siddhartha Mukherjee", "Biologia", 4, 3, 2);
//        BookDAO.addBook("Princípios de Bioquímica de Lehninger", "David L. Nelson", "Biologia", 5, 8, 6);
//        BookDAO.addBook("Cálculo Vol. 1", "James Stewart", "Matemática", 6, 6, 4);
//        BookDAO.addBook("A Origem das Espécies", "Charles Darwin", "Biologia", 8, 7, 6);
//        BookDAO.addBook("O Andar do Bêbado: Como o Acaso Determina Nossas Vidas", "Leonard Mlodinow", "Matemática", 7, 4, 3);
//        BookDAO.addBook("Cálculo Vol. 2", "James Stewart", "Matemática", 9, 9, 7);
//        BookDAO.addBook("Uma Breve História do Tempo", "Stephen Hawking", "Matemática", 10, 5, 4);
//        BookDAO.addBook("Código Limpo: Habilidades Práticas do Agile Software", "Robert C. Martin", "T.I", 11, 10, 9);
//        BookDAO.addBook("Engenharia de Software: Uma Abordagem Profissional", "Roger S. Pressman", "T.I", 12, 6, 5);
//        BookDAO.addBook("Algoritmos: Teoria e Prática", "Thomas H. Cormen", "T.I", 13, 8, 7);
//        BookDAO.addBook("Química: A Ciência Central", "Theodore L. Brown", "Química", 14, 7, 6);
//        BookDAO.addBook("Eletromagnetismo", "David J. Griffiths", "Física", 15, 5, 3);
//        BookDAO.addBook("O Universo Numa Casca de Noz", "Stephen Hawking", "Matemática", 16, 6, 4);
//        BookDAO.addBook("Introdução à Química Orgânica", "William H. Brown", "Química", 17, 4, 2);
//        BookDAO.addBook("A História do Brasil Para Quem Tem Pressa", "Marco Antonio Villa", "História", 18, 8, 6);
//        BookDAO.addBook("Teoria da Computação", "John C. Martin", "T.I", 19, 7, 5);
//        BookDAO.addBook("Física para Cientistas e Engenheiros", "Paul A. Tipler", "Física", 20, 9, 8);
//        BookDAO.addBook("A Era dos Impérios: 1875-1914", "Eric J. Hobsbawm", "História", 21, 6, 5);
//        BookDAO.addBook("A História do Mundo em 6 Copos", "Tom Standage", "História", 22, 7, 6);
//        BookDAO.addBook("Algoritmos e Estrutura de Dados", "Nivio Ziviani", "T.I", 23, 5, 4);
//        BookDAO.addBook("Química Geral", "Raymond Chang", "Química", 24, 8, 7);
//        BookDAO.addBook("História da Vida Privada no Brasil", "Fernando A. Novais", "História", 25, 9, 8);
//        BookDAO.addBook("Matemática Financeira", "Carlos Patricio Samanez", "Matemática", 26, 6, 5);
//        BookDAO.addBook("Física Clássica: Uma Abordagem Moderna", "José Henrique Toledo", "Física", 27, 7, 6);
//        BookDAO.addBook("A Nova História Militar Brasileira", "Joaquim Ferreira dos Santos", "História", 28, 8, 7);
//        BookDAO.addBook("Matemática para a Vida", "Jeffrey Bennett", "Matemática", 29, 5, 4);

        new LoginScreen(new UserDAO());
    }
}