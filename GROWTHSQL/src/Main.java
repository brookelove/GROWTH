import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Main {
    JTextField jtf_commonName, jtf_price, jtf_vareigationType, jtf_potSize;
    JLabel jl_commonName, jl_price, jl_vareigation, jl_potSize, jl_amount, jl_type, jl_family;
    JSpinner js_amount, js_potSize;
    JComboBox jc_familyBox;
    JRadioButton jr_foliage, jr_succulent;
    ButtonGroup group01;
    JButton jb_add, jb_delete;
    JFrame jFrame;
    JTable jTable;
    JScrollPane scrollPane;
    ArrayList<Plant> plantList;
    Plant plant;
    String header[] = new String[] {
            "ID",
            "COMMON NAME",
            "PRICE($)",
            "POT SIZE (.in)",
            "VARIEGATION",
            "TYPE",
            "AMOUNT",
            "SPECIES"
    };
    // defining the connection
    static Connection conn;

    ResultSet rs;
    int row, col;

    DefaultTableModel dtm = new DefaultTableModel(0, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private void createFamilyTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_categories (" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "category_name text NOT NULL," +
                "family text," +
                "plant_id integer NOT NULL," +
                "FOREIGN KEY (plant_id) REFERENCES tbl_plants(id)" +
                ");";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state
                    .executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tbl_categories'");
            if (!rs.next()) {
                state.executeUpdate(sql);
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private void getTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_plants (" +
                "	id integer PRIMARY KEY AUTOINCREMENT," +
                "	common_name text NOT NULL," +
                "	pot_size int NOT NULL," +
                "	price int NOT NULL," +
                "   vareigation text NOT NULL," +
                "   typeOfPlant text NOT NULL," +
                "   amount int NOT NULL" +
                ");";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state
                    .executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tbl_plants'");
            if (!rs.next()) {
                state.executeUpdate(sql);
            }

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private void loadData() throws SQLException {
        // DESCRIPTION: loads data into the table
        plantList = new ArrayList<>();
        Statement state = conn.createStatement();
        rs = state.executeQuery(
                "SELECT * from tbl_plants JOIN tbl_categories ON tbl_plants.id = tbl_categories.plant_id");
        // rs = state.executeQuery(
        // "SELECT p.id, p.common_name, p.price, p.pot_size, p.variegation, p.type,
        // p.amount, c.family " +
        // "FROM tbl_plants p " +
        // "JOIN tbl_categories c ON p.id = c.plant_id");

        plantList.clear();
        while (rs.next()) {
            plantList.add(new Plant(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getFloat(3), rs.getString(5),
                    rs.getString(6), rs.getInt(7), rs.getString(8)));
        }
        dtm.setRowCount(0);
        for (int i = 0; i < plantList.size(); i++) {
            Object[] plantObj = { plantList.get(i).id, plantList.get(i).commonName, plantList.get(i).potSize,
                    plantList.get(i).price, plantList.get(i).vareigationType, plantList.get(i).typeOfPlant,
                    plantList.get(i).amountOwned, plantList.get(i).species };
            dtm.addRow(plantObj);
        }
    }

    public static void main(String[] args) throws SQLException {
        /*
         * DESCRIPTION: Setting up the GUI interface to run and the database and allows
         * for connection of the database, tehn use the getTable to create the table
         * then
         */
        System.out.println("Hello GROWTHSQL!");
        String url = "jdbc:sqlite:plant.db";
        conn = DriverManager.getConnection(url);
        Main main = new Main();
        main.mainGUI();
        main.getTable();
        main.createFamilyTable();
        main.loadData();
    }

    private void mainGUI() {
        /*
         * DESCRIPTION: Setting up GUI interface to add plants to database
         * PRECONDITION: None
         * POSTCONDITION: Will generate the GUI that allows the user to interact with
         */
        jFrame = new JFrame("GROWTH");
        // common name label & input
        jl_commonName = new JLabel("Common Name:");
        jl_commonName.setBounds(10, 10, 150, 40);
        jFrame.add(jl_commonName);
        jtf_commonName = new JTextField();
        jtf_commonName.setBounds(110, 20, 150, 25);
        jFrame.add(jtf_commonName);

        // price label & input
        jl_price = new JLabel("$:");
        jl_price.setBounds(300, 10, 100, 40);
        jFrame.add(jl_price);
        jtf_price = new JTextField();
        jtf_price.setBounds(340, 20, 100, 25);
        jFrame.add(jtf_price);
        // Vareigation label & input
        jl_vareigation = new JLabel("Varrigation:");
        jl_vareigation.setBounds(10, 40, 100, 40);
        jFrame.add(jl_vareigation);
        jtf_vareigationType = new JTextField();
        jtf_vareigationType.setBounds(85, 50, 100, 25);
        jFrame.add(jtf_vareigationType);

        // // PotSize label & input
        jl_potSize = new JLabel("Pot Size (in.):");
        jl_potSize.setBounds(300, 40, 100, 40);
        jFrame.add(jl_potSize);
        js_potSize = new JSpinner();
        js_potSize.setBounds(400, 50, 50, 25);
        jFrame.add(js_potSize);

        // Amount label & input
        jl_amount = new JLabel("Amount:");
        jl_amount.setBounds(10, 70, 100, 40);
        jFrame.add(jl_amount);
        js_amount = new JSpinner();
        js_amount.setBounds(65, 75, 50, 30);
        jFrame.add(js_amount);

        // ComboBox label and selection
        jl_family = new JLabel("Species:");
        jl_family.setBounds(10, 115, 100, 20);
        jFrame.add(jl_family);
        String[] choices = { "Hoya", "Pilea", "Monstera", "Philodendron", "Ficus", "Cissus", "Dracaena", "Cereus",
                "Cotyledon", "Opuntia" };
        jc_familyBox = new JComboBox<String>(choices);
        jc_familyBox.setBounds(65, 115, 100, 20);
        jFrame.add(jc_familyBox);

        // type label and buttons
        group01 = new ButtonGroup();

        jl_type = new JLabel("Type of Plant: ");
        jl_type.setBounds(300, 70, 100, 40);
        jFrame.add(jl_type);
        // // Foliage
        jr_foliage = new JRadioButton("Foliage");
        jr_foliage.setBounds(400, 80, 100, 25);
        group01.add(jr_foliage);
        jFrame.add(jr_foliage);
        // // Succulent
        jr_succulent = new JRadioButton("Succulent");
        jr_succulent.setBounds(480, 80, 100, 25);
        group01.add(jr_succulent);
        jFrame.add(jr_succulent);

        jb_add = new JButton();
        jb_add.setText("Add");
        jb_add.setBounds(630, 10, 100, 25);
        jFrame.add(jb_add);
        jb_add.addActionListener(addPlant);

        jb_delete = new JButton("Update");
        jb_delete.setBounds(630, 60, 100, 25);
        jFrame.add(jb_delete);
        jb_delete.addActionListener(updatePlant);

        jb_delete = new JButton("Delete");
        jb_delete.setBounds(630, 40, 100, 25);
        jFrame.add(jb_delete);
        jb_delete.addActionListener(deletePlant);

        jTable = new JTable();
        jTable.setModel(dtm);
        dtm.setColumnIdentifiers(header);
        scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(10, 170, 750, 600);
        jFrame.add(scrollPane);

        // size of the frame
        jFrame.setSize(800, 800);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    public String getPlantType() {
        if (jr_foliage.isSelected()) {
            return "Foliage";
        } else if (jr_succulent.isSelected()) {
            return "Succulent";
        } else {
            return null;
        }
    }

    ActionListener addPlant = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commonName = jtf_commonName.getText().toString();
            Integer potSize = (Integer) js_potSize.getValue();
            String typeOfPlant = getPlantType();
            Float price = Float.parseFloat(jtf_price.getText().toString());
            String vareigation = jtf_vareigationType.getText().toString();
            Integer amount = (Integer) js_amount.getValue();
            String family = jc_familyBox.getSelectedItem().toString();
            System.out.println(family);

            if (jr_foliage.isSelected()) {

            }
            if (commonName.isEmpty() || price == null || potSize == null || vareigation.isEmpty()
                    || typeOfPlant.isEmpty() || amount == null) {
                JOptionPane.showMessageDialog(jFrame, "Please enter Plant information");
                // jtf_commonName.requestFocus();
            } else {
                int result = JOptionPane.showConfirmDialog(jFrame, "Want to add " + commonName + "?", "Insert",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Statement state = conn.createStatement();
                        state.executeUpdate(
                                "INSERT INTO tbl_plants (`common_name`,`pot_size`, `price`, `vareigation`,`typeOfPlant`, `amount`) VALUES ('"
                                        +
                                        commonName + "','" + potSize + "','" + price + "','" + vareigation + "','"
                                        + typeOfPlant + "','" + amount + "')");
                        ResultSet generatedKeys = state.getGeneratedKeys();
                        int plantId = -1;
                        if (generatedKeys.next()) {
                            plantId = generatedKeys.getInt(1);
                        }

                        // Insert into tbl_categories with the plant ID and family information
                        state.executeUpdate(
                                "INSERT INTO tbl_categories (`category_name`, `family`, `plant_id`) VALUES ('"
                                        + commonName + "','" + family + "','" + plantId + "')");
                        loadData();

                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };
    ActionListener deletePlant = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow != -1) {
                int plantId = (int) jTable.getValueAt(selectedRow, 0);
                String commonName = (String) jTable.getValueAt(selectedRow, 1);
                int result = JOptionPane.showConfirmDialog(jFrame,
                        "Are you sure you want to delete " + commonName + "?", "Delete",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Statement state = conn.createStatement();

                        // Delete entry from tbl_categories first
                        state.executeUpdate("DELETE FROM tbl_categories WHERE plant_id = " + plantId);

                        // Delete entry from tbl_plants
                        state.executeUpdate("DELETE FROM tbl_plants WHERE id = " + plantId);

                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "Please select a plant to delete.");
            }
        }
    };
    ActionListener updatePlant = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow != -1) {
                int plantId = (int) jTable.getValueAt(selectedRow, 0);
                String commonName = jtf_commonName.getText().toString();
                Integer potSize = (Integer) js_potSize.getValue();
                String typeOfPlant = getPlantType();
                Float price = Float.parseFloat(jtf_price.getText().toString());
                String vareigation = jtf_vareigationType.getText().toString();
                Integer amount = (Integer) js_amount.getValue();
                String family = jc_familyBox.getSelectedItem().toString();
                int result = JOptionPane.showConfirmDialog(jFrame,
                        "Are you sure you want to update " + commonName + "?", "Update",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Statement state = conn.createStatement();

                        // Delete entry from tbl_categories first
                        String updateQuery = "UPDATE tbl_plants SET common_name = '" + commonName + "', pot_size = "
                                + potSize +
                                ", price = " + price + ", vareigation = '" + vareigation + "', typeOfPlant = '"
                                + typeOfPlant +
                                "', amount = " + amount + " WHERE id = " + plantId;

                        String updateQueryCategories = "UPDATE tbl_categories SET family = '" + family
                                + "' WHERE plant_id = " + plantId;
                        ;
                        state.executeUpdate(updateQueryCategories);
                        state.executeUpdate(updateQuery);
                        loadData();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "Please select a plant to update.");
            }
        }
    };
}