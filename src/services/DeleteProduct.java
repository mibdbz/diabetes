
package services;

import Connection.Queries;
import Connection.SqlImplementation;
import diabetes.UserInput;
import java.sql.SQLException;
import search.Controller;

/**
 *Klasa kasująca produkt z bazy danych po podaniu nazwy
 * korzysta z wyszukiwarki produktow
 */
public class DeleteProduct {
    UserInput input = new UserInput();
    
    /**
     * Metoda kasuje wybrany produkt z bazy danych
     */
    public void delete(){
        Controller controller = new Controller();
        SqlImplementation sql = new SqlImplementation();
        Queries queries = new Queries();
        
        input.DeleteWhatName(); // jakie imie skasowac
        controller.startSearch(); // wlaczenie wyszukiwarki i wpisanie do listy
        String productName = controller.getProduct().getProductName(); // wyciagniecie nazwy z listy
        
        if(input.ifSure(productName)){ // potwierdzenie checi skasowania
            try {
                String query = queries.deleteProduct(productName); //kwerenda
                sql.execute(query); // wpisanie zapytania do bazy danych
                System.out.println("Produkt skasowany"); 
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
