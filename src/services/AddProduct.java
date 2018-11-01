
package services;

import Connection.Queries;
import Connection.SqlImplementation;
import diabetes.UserInput;
import java.sql.SQLException;
import search.ProductModel;

/**
 *Klasa Dodajaca nowy produkt do bazy danych
 * 
 */
public class AddProduct {
    UserInput input = new UserInput();
    Queries query = new Queries();
    SqlImplementation sql = new SqlImplementation();
    
    /**
     * Metoda dodaje produkt do bazy danych.
     * pobiera pytania z UserInput, wpisuje podane dane do modelu w Queries,
     * a następnie wysyła zapytanie za pomocą SQLImplementation
     */
    public void addProduct(){
        ProductModel model = input.addProduct(); // pytania
        
        try {
            sql.execute(query.addProduct(model)); // pobranie zapytania i przekazanie go do egzekucji
            System.out.println("Produkt dodany do bazy danych");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
