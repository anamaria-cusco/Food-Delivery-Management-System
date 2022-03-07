package BusinessLayer;

import Utils.DisplayAs;

import java.util.Objects;

public class BaseProduct extends MenuItem {

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, double price) {
       super(title,rating,calories,protein,fat,sodium,price);
    }
    @Override
    public double computePrice(){
        return price;
    }

    @Override
    public String computeName() {
        return title;
    }

    @Override
    public double computeRating() {
        return rating;
    }

    @Override
    public int computeCalories() {
        return calories;
    }

    @Override
    public int computeProtein() {
        return protein;
    }

    @Override
    public int computeFat() {
        return fat;
    }

    @Override
    public int computeSodium() {
        return sodium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title + " " + price +"\n";
    }
}
