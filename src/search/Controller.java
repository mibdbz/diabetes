
package search;

import Connection.Queries;
import Connection.SqlImplementation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa rozdzielająca zadania i skupiająca w sobie cała logikę wyszukiwarki produktów
 * @author mibdbz
 */
public class Controller {
    private int count = 1;
    private final Scanner scanner = new Scanner(System.in);
    private final Queries queries = new Queries();
    private List <String> list = new ArrayList<>();
    private final SqlImplementation sql = new SqlImplementation();
    private ProductModel model = new ProductModel();
    
    
    
    public ProductModel getProduct(){
        return model;
    }
    
    /**
     * Metoda agregująca inne metody i startująca wyszukiwanie
     * Wyświetla produkty pasujące do podanej zbitki liter
     */
    public void startSearch(){
        System.out.print("Szukaj: ");
        String toSearch = scanner.nextLine();
        
        list = queries.query(toSearch);
        for(String i: list){
            System.out.println(count + ". " + i);
            count++;
        }
        try {
            getEntireProduct();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metoda do podania numeru produktu który wyświetlił się na ekranie
     * @return 
     */
    private int choice(){
        System.out.print("ktory numer cie intersuje: ");
        int choice = scanner.nextInt();
        return choice;
    }
    
    /**
     * Metoda pobierająca z bazy produkt, który wybrał użytkownik
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void getEntireProduct() throws ClassNotFoundException, SQLException{
        int choice;
        
        if(count > 1){
            choice = choice();
            String name = queries.modelQuery(list.get(choice - 1));
            List <ProductModel> singleProduct = sql.execute(true, name);
            model = singleProduct.get(0);
        } else 
        {
            System.out.println("Nie ma takiego produktu");
        }
    }
}
