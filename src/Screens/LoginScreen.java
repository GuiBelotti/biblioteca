package Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    public LoginScreen(){

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));


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

        pack();

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                dispose();
            }
        });
    }
}
