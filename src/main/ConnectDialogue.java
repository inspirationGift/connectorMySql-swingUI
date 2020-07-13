package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class ConnectDialogue extends JDialog implements ActionListener {

    JLabel lHost = new JLabel("Host Name");
    JTextField hostField = new JTextField();
    JLabel lPort = new JLabel("Port");
    JTextField portField = new JTextField();
    JLabel lDatabase = new JLabel("Database");
    JTextField databaseField = new JTextField();
    JLabel lUser = new JLabel("User Name");
    JTextField userField = new JTextField();
    JLabel lPass = new JLabel("Password");
    JTextField passField = new JPasswordField();

    JButton ok = new JButton("Ok");
    JButton cancel = new JButton("Cancel");
    boolean isCancelled = true;

    Properties properties;

    public ConnectDialogue(JFrame owner, String title, Properties p) {
        super(owner, title, true);
        setSize(450, 250);
        setLocation(330, 230);
        properties = new Properties(p);
        ok.setPreferredSize(new Dimension(75, 25));
        ok.addActionListener(this);
        cancel.setPreferredSize(new Dimension(75, 25));
        cancel.addActionListener(this);

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        panel.setLayout(new GridLayout(5, 2));

        panel.add(lHost);
        panel.add(hostField);
        panel.add(lPort);
        panel.add(portField);
        panel.add(lDatabase);
        panel.add(databaseField);
        panel.add(lUser);
        panel.add(userField);
        panel.add(lPass);
        panel.add(passField);

        panel2.add(ok);
        panel2.add(cancel);

        add(panel, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);
    }

    public Properties getProperties() {
        properties.setProperty("Database", databaseField.getText());
        properties.setProperty("Host_Name", hostField.getText());
        properties.setProperty("Port", portField.getText());
        properties.setProperty("User_Name", userField.getText());
        return properties;
    }

    public String getPassword() {
        return passField.getText();
    }

    public void setPassField(JTextField passField) {
        this.passField = passField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            isCancelled = false;
            this.dispose();
        } else if (e.getSource() == cancel) {
            isCancelled = false;
            this.dispose();
        }


    }
}
