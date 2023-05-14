abstract class Plant {
    // holds all of the common attributes with plants [leaves, varigation, light,
    // water]
    public String name;
    private Float age;
    private String lightSource;
    public Boolean hasLeaves;
    public Boolean hasVarigation;

    public abstract void waterPlants();

    public abstract void grow();

    // steps to make it grow in the optimal condition
    public void varigation() {
        // If there was varigation then the plant color needs to be said
    }

    class Succulents extends Plant {
        // adding in succulent characteristics that make it differnent from others
        private Boolean hasSpikes;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Flowering extends Plant {
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

    class nonFlowering extends Plant {
        // adding in nonFlowering characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Ferns extends Plant {
        // adding in ferns characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Mosses extends Plant {
        // adding in mosses characteristics that make it differnent from others
        // private Boolean hasGrowths;
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Herbs extends Plant {
        // adding in succulent characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Shrubs extends Plant {
        // adding in succulent characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Trees extends Plant {
        // adding in trees characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Climbers extends Plant {
        // adding in climbers characteristics that make it differnent from others
        public Float vineLength;
        public Float amountVines;

        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }

    class Aquatics extends Plant {
        // adding in aquatic characteristics that make it differnent from others
        @Override
        public void waterPlants() {
            // differrent kind of watering type than the
        }

        @Override
        public void grow() {

        }
    }
}
