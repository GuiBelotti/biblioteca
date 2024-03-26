package Screens;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen(){

        setVisible(true);
        //setSize(600 ,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        pack();

        setTitle("Login");

        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel labelUsuario = new JLabel("Usuário:");
        JTextField fieldUsuario = new JTextField(15);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField fieldSenha = new JPasswordField(20);

        panel.add(labelUsuario);
        panel.add(fieldUsuario);
        panel.add(labelSenha);
        panel.add(fieldSenha);

        JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botaoLogin = new JButton("Login");
        panelBotao.add(botaoLogin);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(panelBotao, BorderLayout.SOUTH);

        setContentPane(contentPane);


    }


}
