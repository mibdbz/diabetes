
package diabetes;

import java.util.InputMismatchException;
import java.util.Scanner;
import search.Controller;
import search.ProductModel;
import services.AddProduct;
import services.DeleteProduct;

/**
 *Klasa do grupowania wszystkich innch klas.
 * Zawiera mmenu i caly program flow
 * @author mibdbz
 */
public class ProgramStart {
    private Scanner scanner = new Scanner(System.in);
    
    private void menu(){
        System.out.println("1. Szukaj");
        System.out.println("2. Dodaj");
        System.out.println("3. Usuń");
        System.out.println("4. Zmień");
        System.out.println("0. Wyjdź");
        System.out.print("wybor: ");  
    }
    
    private int ask(){
        try{
            int choice = scanner.nextInt();
            return choice;
        } catch(InputMismatchException ex){
            System.out.println("To nie jest liczba!");
            scanner.nextLine();
        }
        return 10;
    } 
    
    public void start(){
        int choice = 0;
        do{
            menu();
            choice = ask();
            
            switch (choice) {
                case 1:
                    Controller controller = new Controller();
                    controller.startSearch();
                    ProductModel product = controller.getProduct();
                    
                    if(product.getProductName() == null){
                        break;
                    } else {
                        UserInput input = new UserInput();     
                        int howMuch = input.howMuchEat();
                        measurerementsCalculations(product, howMuch);
                    }
                    
                    
                    break;
                case 2:
                    AddProduct add = new AddProduct();
                    add.addProduct();
                    break;
                case 3:
                    DeleteProduct delete = new DeleteProduct();
                    delete.delete();
                    break;
                case 4:
                    System.out.println("NOT YET");
                    break;
                default:
                    if(choice == 0){
                        System.exit(0);
                    } else {
                        System.out.println("Nieprawidłowy wybór. Spróbuj jeszcze raz");
                    }
                    break;
            }
        }while(choice != 0);
        
    }
    
    private void measurerementsCalculations(ProductModel product, int howMuchEat){
        double ww = ((product.getCarbohydrates() / product.getMeasureInGrams()) * howMuchEat) / 10;
        double proteinKcal = ((product.getProtein() / product.getMeasureInGrams()) * howMuchEat) * 4;
        double fatKcal = ((product.getFat() / product.getMeasureInGrams()) * howMuchEat) * 9;
        double wbt = (proteinKcal + fatKcal) / 100;
        
        ww *= 100;
        ww = Math.round(ww);
        ww /= 100;
        
        wbt *=100;
        wbt = Math.round(wbt);
        wbt /= 100;
        
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.println("  WW  |  WBT");
        System.out.println(" " + ww + " | " + wbt);
    }
}
