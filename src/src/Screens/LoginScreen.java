package src.Screens;
import src.features.book.datasource.BookDAO;
import src.features.user.datasource.UserDAO;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen(UserDAO bookDataBase) {

        //Configurações da tela
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 150));
        setMaximumSize(new Dimension(350, 200));

        setTitle("Login");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //elementos
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

        // Função do botão logar
        botaoLogin.addActionListener(e -> {
            String usuario = fieldUsuario.getText().toString();
            String senha = new String(fieldSenha.getPassword()).toString();

            boolean isValidUser = UserDAO.validateUser(usuario, senha);

            if (isValidUser) {
                    dispose();
                    new AdmMenuScreen(usuario, UserDAO.getUserCargo(usuario), BookDAO.getAllBooks(), usuario);
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "Usuário ou senha incorretos. Tente novamente.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });
    }}