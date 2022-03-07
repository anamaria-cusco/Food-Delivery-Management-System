package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    List<MenuItem> components;

    public CompositeProduct(List<MenuItem> components) {
        this.components = components;
    }

    public double computePrice(){
        return components.stream().mapToDouble(MenuItem::computePrice).sum();
    }

    @Override
    public String computeName() {
        String name ="";
        for(MenuItem m: components){
            name +=  " + " + m.computeName();
        }
       name = name.substring(3);
        return name;
    }

    @Override
    public double computeRating() {
        return components.stream().mapToDouble(MenuItem::computeRating).average().getAsDouble();
    }

    @Override
    public int computeCalories() {
        return components.stream().mapToInt(MenuItem::computeCalories).sum();
    }

    @Override
    public int computeProtein() {
        return components.stream().mapToInt(MenuItem::computeProtein).sum();
    }

    @Override
    public int computeFat() {
        return components.stream().mapToInt(MenuItem::computeFat).sum();
    }

    @Override
    public int computeSodium() {
        return components.stream().mapToInt(MenuItem::computeSodium).sum();
    }

    public List<MenuItem> getComponents() {
        return components;
    }
}
