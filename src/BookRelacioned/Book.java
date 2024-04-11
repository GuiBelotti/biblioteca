package BookRelacioned;

public class Book {
    private String title;
    private String author;
    private String category;
    private int isbn;
    private int quantTotal;

    //private int quantEmprestada;

    public Book(String title,String author,String category, int isbn, int quantTotal) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.quantTotal = quantTotal;
        //this.quantDisponivel = quantDisponivel;
    }


    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getQuantTotal() {
        return quantTotal;
    }
    public void setQuantTotal(int quantTotal) {
        this.quantTotal = quantTotal;
    }
//    public int getQuantDisponivel() {
//        return quantDisponivel;
//    }
//    public void setQuantDisponivel(int quantDisponivel) {
//        this.quantDisponivel = quantDisponivel;
//    }



}
