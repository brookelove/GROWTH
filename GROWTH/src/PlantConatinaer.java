import java.util.List;
import java.util.ArrayList;
public class PlantConatinaer<T extends Plant> {
    private List <T> plants;
    public PlantConatinaer() {
        plants = new ArrayList<>();
    }
    public void addPlant(T plant) {
        plants.add(plant);
    }
    public void removePlant(T plant) {
        plants.remove(plant);
    }
    public List<T> getAllPlants() {
        return plants;
    }
}
