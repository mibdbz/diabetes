
package Connection;


import search.ProductModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Implementująca interface SqlDAO.
 * zawiera implementacje metod służących do zapisu, odczytu lub zmiany w bazie danych
 * @author mibdbz
 */
public class SqlImplementation implements SqlDao{
    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;
    
    private ProductModel singleProduct = new ProductModel();
    
    public ProductModel getSingleProduct(){
        return singleProduct;
    }
    
    /**
     * Metoda agregująca inne metody podczas zapisu lub zmiany w bazie
     * @param query przyjmuje zapytanie do bazy danych
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    @Override
    public void execute(String query) throws ClassNotFoundException, SQLException{
        connectToDataBase();
        executeUpdate(query);
        close();
    }
    
    /**
     * Metoda do odczytu z bazy danych
     * @param ifQuery określa czy użyc metody process() służacej do pobrania listy
     * ProductModel czy listy Stringow true == list of productmodel, false == list of String
     * @param query przyjmuje  zapytanie do bazy danych
     * @return zwraca liste ProductModel lub liste Stringow
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List execute(boolean ifQuery, String query) throws ClassNotFoundException, SQLException {
        List<String> listOfStrings;
        List<ProductModel> list;
        connectToDataBase();
        executeQuery(query);
        
        if(ifQuery == true){
            list = process();
            close(ifQuery);
            return list;
        }
        else{
            listOfStrings =  processs();
            close(ifQuery);
            return listOfStrings;
        }
    }

    /**
     * Metoda służy do połączenia z bazą danych.
     * Zawiera sterowniki bazy, inicjalizuje procesy.
     * Zawiera także login i haslo do bazy
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public void connectToDataBase() throws ClassNotFoundException, SQLException{
        final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
        Class.forName(DRIVER_NAME);
        final String SERVER_NAME = "DESKTOP-JCREG3F";
        final String SERVER_PORT = "1521";
        final String SID = "XE";
        final String URL = "jdbc:oracle:thin:@" + SERVER_NAME + ":" + SERVER_PORT + ":" + SID;
        final String USER_NAME = "mibdbz";
        final String USER_PASSWORD = "nokiae51";
        
        conn = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
        //System.out.println("Polaczono z baza danych");
    }

    /**
     * Metoda wysyła zapytanie do bazy danych tylko w przypadku odczytu
     * @param sqlQuery zawiera samo zapytanie
     * @throws SQLException 
     */
    @Override
    public void executeQuery(String sqlQuery) throws SQLException {
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        //System.out.println("Wyslano zapytanie do bazy danych");
    }

    /**
     * Metoda wysyła zapytanie do bazy danych w przypadku zapisu lub zmiany w bazie danych
     * @param sqlUpdate zawiera zapytanie do bazy
     * @throws SQLException 
     */
    @Override
    public void executeUpdate(String sqlUpdate) throws SQLException {
        statement = conn.createStatement();
        statement.executeUpdate(sqlUpdate);
        //System.out.println("Wyslano update do bazy dabych");
    }

    /**
     * Metoda pobiera z bazy danych informacje. Pobiera cały Produkt
     * @return zwraca liste Produktów lub jeden produkt jeśli tylko tyle jest w zapytaniu
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List process()throws ClassNotFoundException, SQLException{
        List<ProductModel> list = new ArrayList<>();
        while(resultSet.next()){
            ProductModel model = new ProductModel();
            model.setId(resultSet.getInt("id"));
            model.setProductType(resultSet.getString("producttype"));
            model.setProductName(resultSet.getString("productname"));
            model.setProtein(resultSet.getDouble("protein"));
            model.setCarbohydrates(resultSet.getDouble("carbohydrates"));
            model.setFat(resultSet.getDouble("fat"));
            model.setMeasureInGrams(resultSet.getInt("measureingrams"));
            model.setKcal(resultSet.getDouble("kcal"));
            list.add(model);
        }
        if(list.size() == 1){
             singleProduct = list.get(0);
        }  
        return list;
    }
    
    /**
     * Metoda do pobierania tylko Nazw Produktów
     * @return zwraca listę Stringów zawierającą Nazwy Produtów
     * @throws SQLException 
     */
    public List<String> processs() throws SQLException{
        List<String> productName = new ArrayList<>();
        String model = "Nie ma takiego produktu";
        while(resultSet.next()){
            model = resultSet.getString("productname");
            productName.add(model);
        }
        return productName;
    }

    /**
     * Metoda zamyka procesy tylko w przypadku zapisu do bazy
     * @throws SQLException 
     */
    @Override
    public void close() throws SQLException{
        statement.close();
        conn.close();
        //System.out.println("Zamknieto procesy");
    }

    /**
     * Metoda zamyka wszystkie procesy w przypadku odczytu z bazy
     * @param ifQuery
     * @throws SQLException 
     */
    @Override
    public void close(boolean ifQuery) throws SQLException {
        statement.close();
        conn.close();
        resultSet.close();
        //System.out.println("Zamknieto wszystkie procesy");
    }
}
