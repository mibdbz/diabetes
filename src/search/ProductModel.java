
package search;

/**
 * Klasa odzwierciedlająca produkt ktory znajduje sie w bazie danych
 * @author mibdbz
 */
public class ProductModel implements Comparable{
    private int id;
    private String productType;
    private String productName;
    private double protein;
    private double carbohydrates;
    private double fat;
    private int measureInGrams;
    private double kcal;
    
    //Constructors

    public ProductModel(int id, String productType, String productName, double protein, double carbohydrates, double fat, int measureInGrams, double kcal) {
        this.id = id;
        this.productType = productType;
        this.productName = productName;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.measureInGrams = measureInGrams;
        this.kcal = kcal;
    }
    
    public ProductModel(){};
    
    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getMeasureInGrams() {
        return measureInGrams;
    }

    public void setMeasureInGrams(int measureInGrams) {
        this.measureInGrams = measureInGrams;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }
    
    //Other Methods
    
    @Override
    public String toString(){
        return productName + "  |  " + protein + "  |  " + carbohydrates + "   |   " + fat +  "   |   " + kcal+"\n";
    }
    

    @Override
    public int compareTo(Object o) {
        ProductModel model = (ProductModel)o;
        
        if(this.id < model.id){
            return -1;
        }
        else if(this.id > model.id) {
            return 1;
        }
        return 0;
    }
    
    
}
