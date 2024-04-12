package BookRelacioned;
import javax.swing.*;
import java.util.*;

public class BookDataBase {

    private List<Book> bookDataBase;

    public List<Book> getBookDataBase() {
        return bookDataBase;
    }

    public BookDataBase(){
        this.bookDataBase = new ArrayList<>();
    }
    public void addBook(String title, String author, String category, int isbn, int quantTotal) {
        boolean isbnExists = false;
        for (Book existingBook : bookDataBase) {
            if (existingBook.getIsbn() == isbn) {
                isbnExists = true;
                break;
            }
        }

        if (isbnExists) {
            JOptionPane.showMessageDialog(null, "Já existe um livro com o mesmo ISBN.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Book newBook = new Book(title, author, category, isbn, quantTotal);
            bookDataBase.add(newBook);
            // Ordena a lista de livros pelo ISBN
            Collections.sort(bookDataBase, Comparator.comparingInt(Book::getIsbn));
            System.out.println("Livro adicionado com sucesso.");
        }
    }

    public void editBook(int isbn, String newTitle, String newAuthor, String newCategory, Integer newIsbn, Integer newQuantTotal) {

        for(Book bookEdit : bookDataBase) {

            if(bookEdit.getIsbn() == isbn) {
                bookEdit.setQuantTotal(newQuantTotal != null ? newQuantTotal : bookEdit.getQuantTotal());
                bookEdit.setTitle(newTitle != null ? newTitle : bookEdit.getTitle());
                bookEdit.setAuthor(newAuthor != null ? newAuthor : bookEdit.getAuthor());
                bookEdit.setCategory(newCategory != null ? newCategory : bookEdit.getCategory());
                bookEdit.setIsbn(newIsbn != null ? newIsbn : bookEdit.getIsbn());
            }
        }
    }
    public void deletedBook(int isbn) {
        Iterator<Book> iterator = bookDataBase.iterator();
        while (iterator.hasNext()) {
            Book bookDeleted = iterator.next();
            if (bookDeleted.getIsbn() == isbn) {
                iterator.remove();
            }
        }
    }

    public void attDispo(int isbn, int newDispo) {

        for(Book bookAttDispo : bookDataBase){
            if(bookAttDispo.getIsbn() == isbn) {
                bookAttDispo.setQuantTotal(newDispo);
            }
        }
        for (Book livro : bookDataBase) {
            System.out.println("\ntitle : " + livro.getTitle() + "\nauthor : " + livro.getAuthor() + "\ncategory : "
                    + livro.getCategory() + "\nisbn : " + livro.getIsbn() + "\nQuantidade: " + livro.getQuantTotal());
        }

    }

    public List<Book> searchBooks(String searchTerm, String searchType) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : bookDataBase) {
            String title = book.getTitle().toLowerCase();
            String author = book.getAuthor().toLowerCase();
            String category = book.getCategory().toLowerCase();

            switch (searchType) {
                case "Título":
                    if (searchTerm.length() >= 2) {
                        if (title.contains(searchTerm)) {
                            searchResults.add(book);
                        }
                    }

                    break;
                case "Autor":
                    if (searchTerm.length() >= 2) {
                        if (author.contains(searchTerm)) {
                            searchResults.add(book);
                        }
                    }
                    break;
                case "Categoria":
                    if (searchTerm.length() >= 2) {
                        if (category.contains(searchTerm)) {
                            searchResults.add(book);
                        }
                    }
                    break;
                case "ISBN":
                    String isbnString = String.valueOf(book.getIsbn());
                    if (isbnString.equalsIgnoreCase(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                default:
                    break;
            }

        }

        return searchResults;
    }

//    public void emprestimoDevolucaoBook(String funcao, int isbn){
//
//        if(funcao.equals("emprestar")) {
//            for (Book emprestarBook : bookDataBase){
//                if (isbn == emprestarBook.getIsbn()){
//                    if (emprestarBook.getQuantDisponivel() != 0) {
//                        emprestarBook.setQuantDisponivel(emprestarBook.getQuantDisponivel() - 1);
//                    } else {
//                        emprestarBook.setQuantDisponivel(emprestarBook.getQuantDisponivel());
//                        //trocar dps para mostrar erro na tela ou vermelho sem conseguir mudar o valor
//                    }
//                }
//            }
//        }
//        if(funcao.equals("devolver")) {
//            for (Book devolverBook : bookDataBase){
//                if (isbn == devolverBook.getIsbn()){
//                    if (devolverBook.getQuantTotal() != devolverBook.getQuantDisponivel()) {
//                        devolverBook.setQuantDisponivel(devolverBook.getQuantDisponivel() + 1);
//                    }
//                    //fazer um else para caso n tenha livro emprestadp
//                }
//            }
//        }
//        for (Book livro : bookDataBase) {
//            System.out.println("\ntitle : " + livro.getTitle() + "\nauthor : " + livro.getAuthor() + "\ncategory : "
//                    + livro.getCategory() + "\nisbn : " + livro.getIsbn() + "\nQuantidade: " + livro.getQuantTotal());
//        }
//    }
}