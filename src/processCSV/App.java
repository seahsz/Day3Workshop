package processCSV;

public class App {

    // Members
    private final String name;
    private final String category;
    private final double rating;

    // Constructor
    public App(String name, String category, double rating) {
        this.name = name;
        this.category = category;
        this.rating = rating;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "App {name:%s, category:%s, rating:%f}".formatted(name, category, rating);
    }
    
}
