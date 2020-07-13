package main;

import javax.swing.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        JFrame jf = new JFrame("Database");
        Properties props = new Properties();
        ConnectDialogue connectDialogue = new ConnectDialogue(jf, "Database connector", props);
        connectDialogue.setVisible(true);
        if (connectDialogue.isCancelled) System.exit(0);
        DbConnector dbConnector = new DbConnector(connectDialogue.getProperties(), connectDialogue.getPassword());
        if (!dbConnector.open()) System.exit(0);

        Database db = new Database(dbConnector);
        jf.setSize(800, 600);
        jf.add(db);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
