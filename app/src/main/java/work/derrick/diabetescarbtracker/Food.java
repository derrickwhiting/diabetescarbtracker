package work.derrick.diabetescarbtracker;

/**
 * Created by derrickwhiting on 8/20/16.
 */
public class Food {
    private String mDescription;
    private int mCarb;
    private double mWeight;

    public Food(String description, int carb, double weight){
        mDescription = description;
        mCarb = carb;
        mWeight = weight;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getCarbPerWeight(){
        return mCarb / mWeight;
    }

    public int getCarb(){
        return mCarb;
    }
}
