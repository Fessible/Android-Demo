package example.com.shopdemo;

/**
 * Created by rhm on 2017/4/11.
 */

public class Food {
    private int imageId;
    private String foodName;
    private float foodPrice;

    public void Food(int imageId, String foodName, float foodPrice) {
        this.imageId = imageId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
