import java.util.List;
import java.util.ArrayList;
public class PlantConatinaer<T extends Plant> {
    /*
    * Usage of Generic class to list, add and delete plant*/
    private List <T> plants;
    public PlantConatinaer() {
        plants = new ArrayList<>();
    }
    public void addPlant(T plant) {
        plants.add(plant);
    }
    public void updatePlant(T plant) {
        //would need to get plant by id
        //then update plant
        //remove plant
        //then re-add plant
    }
    public void removePlant(T plant) {
        plants.remove(plant);
    }
    public List<T> getAllPlants() {
        return plants;
    }
}
