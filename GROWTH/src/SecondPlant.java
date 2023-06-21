public class SecondPlant {
    // Second version of aplant class with out the use of abstract classes
    private Integer id, potSize, amount;
    private String commonName, varegationType;
    private Float price;

    public SecondPlant(Integer id, String commonName, Integer potSize, Float price, String varegationType,
            Integer amount) {
        this.id = id;
        this.commonName = commonName;
        this.potSize = potSize;
        this.price = price;
        this.varegationType = varegationType;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return commonName;
    }

    public String toString() {
        return "MyObject{" +
                "name='" + commonName + ", \n" +
                "id: " + id + ", \n" +
                '}';
    }

    class SSucculent extends SecondPlant {
        String hasSpines;

        public SSucculent(Integer id, String commonName, Integer potSize, Float price, String variegation,
                Integer amount, String hasSpines) {
            super(id, commonName, potSize, price, variegation, amount);
            this.hasSpines = hasSpines;
        };
    }

    class SFoliage extends SecondPlant {
        public SFoliage(Integer id, String commonName, Integer potSize, Float price, String variegation,
                Integer amount) {
            super(id, commonName, potSize, price, variegation, amount);
        }

        public void pruneLeaves() {
            System.out.printf(
                    "Please check your leaves of the %s plant to make sure that there are no pre-dying leaves",
                    getName());
        }
    }
}
