
package diabetes;


import java.util.Scanner;
import search.ProductModel;

public class UserInput {
    // to do
//    aktualizacja produktu;
//    zapodaj wszyskite produkty;
//    produkty po typie produktu;
//   jakie sa typy produktow
    
    
    private final Scanner scanner = new Scanner(System.in);
    private ProductModel model = new ProductModel();
    
    
    public ProductModel addProduct(){
        System.out.print("Podaj nazwe produktu[bez polskich znakow]: "); 
            model.setProductName(scanner.nextLine());
        System.out.print("Podaj typ produktu: ");
            model.setProductType(scanner.nextLine());
        System.out.print("Podaj na ile gram produktu bedziesz podawała wartości: ");
            model.setMeasureInGrams(scanner.nextInt());
        System.out.print("Podaj białka na "+ model.getMeasureInGrams() + " gram: ");
            model.setProtein(scanner.nextDouble());
        System.out.print("Podaj węglowodany na " + model.getMeasureInGrams() + " gram: ");
            model.setCarbohydrates(scanner.nextDouble());
        System.out.print("Podaj tłuszcze na " + model.getMeasureInGrams() + " gram: ");
            model.setFat(scanner.nextDouble());
        System.out.print("Podaj kalorie na " + model.getMeasureInGrams() + " gram: ");
            model.setKcal(scanner.nextDouble());
        return model;
    }
    
    
    public void DeleteWhatName(){
        System.out.println("Produkt o jakiej nazwie chcesz skasowac: ");
    }
    
    /**
     * Metoda potwierdza chec skasowania produktu
     * @param name nazwa produktu
     * @return decyzja czy skasowac czy nie
     */
    public boolean ifSure(String name){
        System.out.print("Czy jestes pewna, że chcesz skasować "+name+"?[t/n]: ");
        String answer = scanner.nextLine();
        if(answer.equals("t") || answer.equals("T")|| answer.equals("y") || answer.equals("Y")){
            return true;
        }
        return false;
    }
    
    
    public String update(){
        System.out.println("Który produkt chcesz zaktualizowac?");
        System.out.print("Podaj nazwe: ");
        String name = scanner.nextLine();
        return name;
    }
    
    public String updateMenu(){
        System.out.println("Jaka wartość chcesz zmienic?");
        System.out.println("Dostępne wartości: ");
        System.out.println("1. nazwa");
        System.out.println("2. typ");
        System.out.println("3. białka");
        System.out.println("4. węglowodany");
        System.out.println("5. tłuszcze");
        System.out.println("6. kalorie");
        System.out.println("0. wyjście");
        System.out.print("wybór: ");
        
       
        
            int choice = scanner.nextInt();
            
            do{
            switch(choice){
                case 1: return "productname"; 
                case 2: return "producttype";
                case 3: return "protein";
                case 4: return "carbohydrates";
                case 5: return "fat";
                case 6: return "kcal";
                case 0: System.exit(0);
                default: System.out.println("Nieprawidłowy wybór"); 
            }
            }while((choice > 6) || (choice < 1));
    return "Nieprawidłowy wybór";        
    }
    
    public String newValue(){
        System.out.print("Podaj nową wartość zmienianego produktu: ");
        scanner.nextLine(); // czyszczenie bufora
        String newValue = scanner.nextLine();
        return newValue;
    }

    public int howMuchEat(){
        System.out.print("Ile tego zjadłas: ");
        int howMuch = scanner.nextInt();
        return howMuch;
    }
            
}
