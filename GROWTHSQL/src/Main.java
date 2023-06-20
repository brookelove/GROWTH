import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Main {
    JTextField jtf_commonName, jtf_price, jtf_vareigationType, jtf_potSize;
    JLabel jl_commonName, jl_price, jl_vareigation, jl_potSize, jl_amount, jl_type;
    JSpinner js_amount, js_potSize;
    JRadioButton jr_foliage, jr_succulent;
    ButtonGroup group01;
    JButton jb_add, jb_delete, jb_update, jb_foliage, jb_succulent;
    JFrame jFrame;
    JTable jTable;
    JScrollPane scrollPane;
    ArrayList<Plant> plantList;
    Plant plant;
    String header[] = new String[] {
            "ID",
            "COMMON NAME",
            "PRICE",
            "POT SIZE",
            "VARIEGATION",
            "TYPE",
            "AMOUNT"
    };
    static Connection conn;
    ResultSet rs;
    int row, col;

    DefaultTableModel dtm = new DefaultTableModel(0, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private void getTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_plants (" +
                "	id integer PRIMARY KEY AUTOINCREMENT," +
                "	common_name text NOT NULL," +
                "	pot_size int NOT NULL," +
                "	price int NOT NULL" +
                "   vareigation text NOT NULL" +
                "   typeOfPlant text NOT NULL" +
                "   amount int NOT NULL" +
                ");";
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    private void loadData() throws SQLException {
        plantList = new ArrayList<>();
        Statement state = conn.createStatement();
        rs = state.executeQuery("select * from tbl_plants");
        plantList.clear();
        while (rs.next()) {
            plantList.add(new Plant(rs.getInt(1), rs.getString(1), rs.getInt(3), rs.getFloat(4), rs.getString(5),
                    rs.getString(6), rs.getInt(7)));
        }
        dtm.setRowCount(0);
        for (int i = 0; i < plantList.size(); i++) {
            Object[] plantObj = { plantList.get(i).id, plantList.get(i).commonName, plantList.get(i).potSize,
                    plantList.get(i).price, plantList.get(i).vareigationType, plantList.get(i).typeOfPlant,
                    plantList.get(i).amountOwned };
            dtm.addRow(plantObj);
        }
    }

    public static void main(String[] args) throws SQLException {
        /*
         * DESCRIPTION: Setting up the GUI interface to run and the database
         */
        System.out.println("Hello GROWTHSQL!");
        String url = "jdbc:sqlite:plant.db";
        conn = DriverManager.getConnection(url);
        Main main = new Main();
        main.mainGUI();
        main.getTable();
        main.loadData();
    }

    private void mainGUI() {
        /*
         * DESCRIPTION: Setting up GUI interface to add plants to database
         * PRECONDITION: None
         * POSTCONDITION: Will generate the GUI that allows the user to interact with
         */
        jFrame = new JFrame();
        // common name label & input
        jl_commonName = new JLabel();
        jl_commonName.setText("Common Name:");
        jl_commonName.setBounds(10, 10, 150, 40);
        jFrame.add(jl_commonName);
        jtf_commonName = new JTextField();
        jtf_commonName.setBounds(110, 20, 150, 25);
        jFrame.add(jtf_commonName);

        // price label & input
        jl_price = new JLabel();
        jl_price.setText("Price:");
        jl_price.setBounds(300, 10, 100, 40);
        jFrame.add(jl_price);
        jtf_price = new JTextField();
        jtf_price.setBounds(340, 20, 100, 25);
        jFrame.add(jtf_price);
        // Vareigation label & input
        jl_vareigation = new JLabel();
        jl_vareigation.setText("Varrigation:");
        jl_vareigation.setBounds(10, 40, 100, 40);
        jFrame.add(jl_vareigation);
        jtf_vareigationType = new JTextField();
        jtf_vareigationType.setBounds(85, 50, 100, 25);
        jFrame.add(jtf_vareigationType);

        // // PotSize label & input
        jl_potSize = new JLabel();
        jl_potSize.setText("Pot Size:");
        jl_potSize.setBounds(300, 40, 100, 40);
        jFrame.add(jl_potSize);
        js_potSize = new JSpinner();
        js_potSize.setBounds(350, 50, 50, 25);
        jFrame.add(js_potSize);

        // Amount label & input
        jl_amount = new JLabel();
        jl_amount.setText("Amount:");
        jl_amount.setBounds(10, 70, 100, 40);
        jFrame.add(jl_amount);
        js_amount = new JSpinner();
        js_amount.setBounds(65, 75, 50, 30);
        jFrame.add(js_amount);

        // type label and buttons
        group01 = new ButtonGroup();

        jl_type = new JLabel();
        jl_type.setText("Type of Plant: ");
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
        jb_add.setBounds(130, 140, 100, 25);
        jFrame.add(jb_add);
        jb_add.addActionListener(addPlant);

        jb_delete = new JButton();
        jb_delete.setText("Delete");
        jb_delete.setBounds(230, 140, 100, 25);
        jFrame.add(jb_delete);
        // jb_delete.addActionListener(delPlant);

        jb_update = new JButton();
        jb_update.setText("Update");
        jb_update.setBounds(330, 140, 100, 25);
        jFrame.add(jb_update);
        // jb_update.addActionListener(updatePlant);

        jTable = new JTable();
        jTable.setModel(dtm);
        dtm.setColumnIdentifiers(header);
        scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(10, 170, 550, 600);
        jFrame.add(scrollPane);
        // jTable.addMouseListener(mouseListener);

        // size of the frame
        jFrame.setSize(600, 800);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    ActionListener addPlant = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commonName = jtf_commonName.getText().toString();
            Integer potSize = (Integer) js_potSize.getValue();
            String typeOfPlant = group01.getSelection().toString();
            Float price = Float.parseFloat(jtf_price.getText().toString());
            String vareigation = jtf_vareigationType.getText().toString();
            Integer amount = (Integer) js_amount.getValue();
            if (commonName.isEmpty() || price == null || potSize == null || vareigation.isEmpty()
                    || typeOfPlant.isEmpty() || amount == null) {
                JOptionPane.showMessageDialog(jFrame, "Please enter Plant information");
                // jtf_commonName.requestFocus();
            } else {
                int result = JOptionPane.showConfirmDialog(jFrame, "Want to insert " + commonName + "?", "Insert",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        Statement state = conn.createStatement();
                        state.executeUpdate(
                                "insert into tbl_plants (`common_name`,`pot_size`, `price`, `vareigation`,`typeOfPlant`, `amount`) VALUES ('"
                                        +
                                        commonName + "','" + potSize + "','" + price + vareigation + "','" + typeOfPlant
                                        + "','" + amount + "')");

                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
            }
        }
    };

}