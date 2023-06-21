import java.util.Collection;
import java.util.concurrent.*;

public class PlantManagement {
    private ExecutorService executor;
    private PlantConatinaer<Plant> plantContainer;

    public PlantManagement() {
        executor = Executors.newFixedThreadPool(5);
        plantContainer = new PlantConatinaer<>();
    }
    public void addManyPlants(Collection<Plant> plants){
        for (Plant plant : plants) {
            executor.execute(() -> {
                plantContainer.addPlant(plant);
                System.out.printf("Adding plant: %s%n", plant.getName());
            });
        }
        shutdown();
    }
    public void shutdown () {
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
