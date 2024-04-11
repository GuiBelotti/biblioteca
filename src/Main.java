import BookRelacioned.BookDataBase;
import Screens.AddAndEditScreen;
import Screens.AdmMenuScreen;
import Screens.LoginScreen;

public class Main {
    public static void main(String[] args) {

        BookDataBase bookDataBase = new BookDataBase();
        bookDataBase.addBook("POO","Carlos","estudo",1,10);
        bookDataBase.addBook("BD","Carlos","estudo",2,10);
        bookDataBase.addBook("Lab POO","Bruno","estudo",3,10);
        bookDataBase.addBook("industria 4.0","Alexandre","estudo",4,10);

        bookDataBase.addBook("Star Wars: A Ascensão Skywalker","Autor Star Wars","Filmes",51, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Harry Potter e a Pedra Filosofal","J.K. Rowling","Filmes",52, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Vingadores: Ultimato","Autor Vingadores","Filmes",53, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Interestelar","Autor Interestelar","Filmes",54, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("O Senhor dos Anéis: O Retorno do Rei","J.R.R. Tolkien","Filmes",55, (int) (Math.random() * 10) + 1);

        bookDataBase.addBook("Clean Code: A Handbook of Agile Software Craftsmanship","Robert C. Martin","T.I",56, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Cracking the Coding Interview","Gayle Laakmann McDowell","T.I",57, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides","T.I",58, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Introduction to Algorithms","Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein","T.I",59, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("The Pragmatic Programmer: Your Journey to Mastery","Andrew Hunt, David Thomas","T.I",60, (int) (Math.random() * 10) + 1);

        bookDataBase.addBook("Biologia Celular e Molecular","Junqueira & Carneiro","Biologia",61, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("O Mundo de Sofia","Jostein Gaarder","Biologia",62, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("A Origem das Espécies","Charles Darwin","Biologia",63, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Sapiens: Uma Breve História da Humanidade","Yuval Noah Harari","Biologia",64, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Genética na Agropecuária","Carlos Frederico Martins","Biologia",65, (int) (Math.random() * 10) + 1);

        bookDataBase.addBook("Cálculo Vol. 1","James Stewart","Matemática",66, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Geometria Analítica","Paulo Boulos, Carlos Américo","Matemática",67, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Álgebra Linear","Howard Anton","Matemática",68, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Fundamentos da Matemática Elementar","Gelson Iezzi, Osvaldo Dolce, Maria Aparecida","Matemática",69, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("O Andar do Bêbado: Como o Acaso Determina Nossas Vidas","Leonard Mlodinow","Matemática",70, (int) (Math.random() * 10) + 1);

        bookDataBase.addBook("Java: Como Programar","Paul J. Deitel, Harvey Deitel","Linguagens de programação",71, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Python Fluente","Luciano Ramalho","Linguagens de programação",72, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("JavaScript: The Good Parts","Douglas Crockford","Linguagens de programação",73, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("C Programming Language","Brian W. Kernighan, Dennis M. Ritchie","Linguagens de programação",74, (int) (Math.random() * 10) + 1);
        bookDataBase.addBook("Estruturas de Dados e Algoritmos em JavaScript","Loiane Groner","Linguagens de programação",75, (int) (Math.random() * 10) + 1);


        //new LoginScreen();
        new AdmMenuScreen("Gabriel", "Admin",bookDataBase.getBookDataBase(), bookDataBase);
        //new AddAndEditScreen("add", bookDataBase);
    }
}


/*

 *   Gestao livro (add,del, edit)
 *   Atualizar disponibilidade
 *   Gestao emprestimo e devolucao
 *   Pesquisar livro tipo
 *
 * */