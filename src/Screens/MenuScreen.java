package Screens;
import javax.swing.*;
import java.awt.*;
public class MenuScreen extends JFrame{

    public MenuScreen(String Name, String function){

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));


        JLabel userName = new JLabel("" + Name);
        JLabel functionUser = new JLabel("" + function);
        JLabel titleMenu = new JLabel("Olá, " + userName.getText() + "\nFunção: " + functionUser.getText());
        JLabel exitEmojiLabel = new JLabel("\u21AA");

        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints0 = new GridBagConstraints();
        constraints0.gridx = 0;
        constraints0.gridy = 0;
        constraints0.anchor = GridBagConstraints.WEST;
        titlePanel.add(titleMenu, constraints0);

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 1; 
        constraints1.gridy = 0;
        constraints1.anchor = GridBagConstraints.EAST;
        titlePanel.add(exitEmojiLabel, constraints1);

        add(titlePanel);
        pack();
    }


}
