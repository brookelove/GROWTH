abstract class Plant {
    // holds all of the common attributes with plants [leaves, varigation, light,
    // water]
    public String commonName;
    private Float age;
    private String lightSource;
    public Boolean hasLeaves;
    public Boolean hasVarigation;

    public abstract void waterPlants();

    public abstract void grow();

    public void varigation() {
        // If there was varigation then the plant color needs to be said
    }

    class Succulent extends Plant {
        // adding in succulent characteristics that make it differnent from others
        private Boolean hasSpikes;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {
            // steps to make it grow in the optimal condition
        }
    }

    class Foliage extends Plant {
        // adding in cactus characteristics that make it differnent from others
        private String bloomColor;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

}
