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
    public void addBook(String title, String author, String category, int isbn, int quantTotal, Integer prazo, boolean dispo) {
        // verifico se o isbn ja existe
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
            //insiro o livro no banco e ordeno pelo isbn

            int prazoValue = prazo != null ? prazo.intValue() : 0;
            Book newBook = new Book(title, author, category, isbn, quantTotal, prazoValue, dispo);
            bookDataBase.add(newBook);
            Collections.sort(bookDataBase, Comparator.comparingInt(Book::getIsbn));
            System.out.println("Livro adicionado com sucesso.");
        }
    }
    public void editBook(int isbn, String newTitle, String newAuthor, String newCategory, Integer newIsbn, Integer newQuantTotal, Integer newTerm) {

        // procuro o livro no banco pelo isbn e mudo os valores dele conforme passado por parametro

        for(Book bookEdit : bookDataBase) {

            if(bookEdit.getIsbn() == isbn) {
                bookEdit.setQuantTotal(newQuantTotal != null ? newQuantTotal : bookEdit.getQuantTotal());
                bookEdit.setTitle(newTitle != null ? newTitle : bookEdit.getTitle());
                bookEdit.setAuthor(newAuthor != null ? newAuthor : bookEdit.getAuthor());
                bookEdit.setCategory(newCategory != null ? newCategory : bookEdit.getCategory());
                bookEdit.setIsbn(newIsbn != null ? newIsbn : bookEdit.getIsbn());
                bookEdit.setPrazo(newTerm != null ? newTerm : bookEdit.getPrazo());
            }
        }
    }
    public void deletedBook(int isbn) {

        //procura o livro pelo isbn passado e deleta ele da lista
        Iterator<Book> iterator = bookDataBase.iterator();
        while (iterator.hasNext()) {
            Book bookDeleted = iterator.next();
            if (bookDeleted.getIsbn() == isbn) {
                iterator.remove();
            }
        }
    }

    //funcao buscar livro
    public List<Book> searchBooks(String searchTerm, String searchType) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : bookDataBase) {
            String title = book.getTitle().toLowerCase();
            String author = book.getAuthor().toLowerCase();
            String category = book.getCategory().toLowerCase();

            //Verifica o tipo de pesquisa que o usuario quer fazer
            //Para fazer a pesquisa precisa de no minimo 2 caracteres
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
        //retorna a lista com os resultados encontrados na busca
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