package BookRelacioned;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Livros")
public class Book {
    @Id
    @GeneratedValue
    private int isbn;
    private String title;
    private String author;
    private String category;
    private int quantTotal;
    private int prazo;
    private boolean dispo;

    public Book(String title,String author,String category, int isbn, int quantTotal, int prazo, boolean dispo) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.quantTotal = quantTotal;
        this.prazo = prazo;
        this.dispo = dispo;
    }

    public Book() { }

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
    public int getPrazo() {
        return prazo;
    }
    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
    public boolean isDispo() {
        return dispo;
    }
    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

}
