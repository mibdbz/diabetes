
package Connection;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface pokazujący jakie metody używane są do zapisywania i wyciagania informacji z bazy danych
 * @author mibdbz
 */
public interface SqlDao {
    void connectToDataBase() throws ClassNotFoundException, SQLException; // połączenie z bazą danych, sterowniki itd.
    void execute(String query)throws ClassNotFoundException, SQLException; // metoda skupiająca inne metody. Uzywana w prypadku zapisu lub zmiany w bazie danych
    Object execute(boolean ifQuery, String query) throws ClassNotFoundException, SQLException; // metoda analogiczna do powyższej lecz służąca do odczytu z bazy
    void executeQuery(String sqlQuery)throws SQLException; // metoda z kwerendą do odczytu z bazy
    void executeUpdate(String sqlUpdate)throws SQLException;// metoda z kwerendą służącą do zmiany lub zapisu
    List process()throws ClassNotFoundException, SQLException;//pobieranie odpowiedzi z bazy danych
    void close()throws SQLException; //zamykanie wszystkich procesów w przypadku zapisu do bazy
    void close(boolean ifQuery)throws SQLException; // zamykanie procesów w prypadku odczytu z bazy
}
