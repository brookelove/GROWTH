import java.util.*;

public class Plant {
    Integer id;
    String commonName;
    Integer potSize;
    Float price;
    String vareigationType;
    String typeOfPlant;
    Integer amountOwned;

    public Plant(Integer id, String commonName, Integer potSize, Float price, String vareigationType,
            String typeOfPlant, Integer amountOwned) {
        this.id = id;
        this.commonName = commonName;
        this.potSize = potSize;
        this.price = price;
        this.vareigationType = vareigationType;
        this.typeOfPlant = typeOfPlant;
        this.amountOwned = amountOwned;
    }
}
