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