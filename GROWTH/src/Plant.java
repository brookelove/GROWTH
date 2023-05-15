public abstract class Plant {
    // holds all of the common attributes with plants [leaves, varigation, light,
    // water]
    // have to create an ID for each plant
    public String commonName;
    public String age;
    // we will be providing the best lightSource information
    public Boolean hasVarigation;
    private Integer potSize;
    private Float price;

    public abstract void waterPlants();

    public abstract void grow();

    public void varigation() {
        // If there was varigation then the plant color needs to be said
    }

    public class Succulent extends Plant {
        private Boolean hasSpikes;
        // private String bloomColor;
        private Boolean hasBloom;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {
            // steps to make it grow in the optimal condition
        }

        public Succulent(String newCommonName, String newAge, Boolean varigation, Integer newPotSize, Boolean spikes,
                String hasBloom) {
            // adding in succulent characteristics that make it differnent from others

        }
    }

    public class Foliage extends Plant {

        public String leafShape;
        public String leafColor;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }

        public Foliage(String newCommonName, String newAge, Boolean varigation, Integer newPotSize, Boolean spikes,
                Boolean hasLeaves) {
            // adding in succulent characteristics that make it differnent from others

        }
    }

    class Mystery extends Plant {
        /// if the plant is a mystery has to ask questions based on color look and ect.
        /// to make work
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }

    }

}
