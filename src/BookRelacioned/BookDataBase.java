package BookRelacioned;
import java.util.ArrayList;
import java.util.List;

public class BookDataBase {

    private List<Book> bookDataBase;

    public List<Book> getBookDataBase() {
        return bookDataBase;
    }

    public BookDataBase(){
        this.bookDataBase = new ArrayList<>();
    }
    public void addBook(String title, String author, String category, int isbn, int quantTotal){

        Book newBook = new Book(title,author,category,isbn,quantTotal);
        bookDataBase.add(newBook);

        System.out.println("Add");
    }

    public void editBook(int isbn, String newTitle, String newAuthor, String newCategory, Integer newIsbn, Integer newQuantTotal, Integer newQuantDisponivel) {

        for (Book livro : bookDataBase) {
            System.out.println("\ntitle : " + livro.getTitle() + "\nauthor : " + livro.getAuthor() + "\ncategory : "
                    + livro.getCategory() + "\nisbn : " + livro.getIsbn() + "Quantidade: " + livro.getQuantTotal());
        }

        for(Book bookEdit : bookDataBase) {
            if(bookEdit.getIsbn() == isbn) {
                //bookEdit.setQuantDisponivel(newQuantDisponivel != null ? newQuantTotal : bookEdit.getQuantDisponivel());
                bookEdit.setQuantTotal(newQuantTotal != null ? newQuantTotal : bookEdit.getQuantTotal());
                bookEdit.setTitle(newTitle != null ? newTitle : bookEdit.getTitle());
                bookEdit.setAuthor(newAuthor != null ? newAuthor : bookEdit.getAuthor());
                bookEdit.setCategory(newCategory != null ? newCategory : bookEdit.getCategory());
                bookEdit.setIsbn(newIsbn != null ? newIsbn : bookEdit.getIsbn());
            }
        }
        System.out.println("\n\nApos edição\n\n");

        for (Book livro : bookDataBase) {
            System.out.println("\ntitle : " + livro.getTitle() + "\nauthor : " + livro.getAuthor() + "\ncategory : "
                    + livro.getCategory() + "\nisbn : " + livro.getIsbn() + "\nQuantidade: " + livro.getQuantTotal());
        }

    }
    public void deletedBook(int isbn) {

        for(Book bookDeleted : bookDataBase) {
            if ((bookDeleted.getIsbn() == isbn)) {
                bookDataBase.remove(bookDeleted);
            }
        }
        System.out.println("\n\nApos remoção\n\n");
        for (Book livro : bookDataBase) {
            System.out.println("\ntitle : " + livro.getTitle() + "\nauthor : " + livro.getAuthor() + "\ncategory : "
                    + livro.getCategory() + "\nisbn : " + livro.getIsbn() + "\nQuantidade: " + livro.getQuantTotal());
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