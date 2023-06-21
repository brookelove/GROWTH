import java.util.*;

public class Plant {
    Integer id;
    String commonName;
    Integer potSize;
    Float price;
    String vareigationType;
    String typeOfPlant;
    Integer amountOwned;
    String species;

    public Plant(Integer id, String commonName, Integer potSize, Float price, String vareigationType,
            String typeOfPlant, Integer amountOwned, String species) {
        this.id = id;
        this.commonName = commonName;
        this.potSize = potSize;
        this.price = price;
        this.vareigationType = vareigationType;
        this.typeOfPlant = typeOfPlant;
        this.amountOwned = amountOwned;
        this.species = species;
    }
}
