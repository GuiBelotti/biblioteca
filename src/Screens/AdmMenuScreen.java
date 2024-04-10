package Screens;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.BiPredicate;

public class AdmMenuScreen extends JFrame{

    public AdmMenuScreen(String Name, String function){

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1));

        Dimension headerButton = new Dimension(150,100);

        JLabel userNameTxtLabel = new JLabel("Óla " + Name);
        JLabel functionUserTxtLabel = new JLabel("Função: " + function);

        JButton exitButton = new JButton("Exit");
        JPanel exitPanel = new JPanel();
        exitPanel.setPreferredSize(headerButton);
        exitPanel.add(exitButton);

        JButton userEditButton = new JButton("Usuarios");
        JPanel userEditPanel = new JPanel();
        userEditPanel.setPreferredSize(headerButton);
        userEditPanel.add(userEditButton);

        JTextField fieldSearchLabel = new JTextField();
        String[] comboBoxOptions = {"Título","Autor","Categoria","ISBN"};
        JComboBox<String> optionsSearchComboBox = new JComboBox<>(comboBoxOptions);

        JButton searchButton = new JButton("Pesquisar");
        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setPreferredSize(headerButton);
        searchButtonPanel.add(searchButton);

        JButton addButton = new JButton("Adicionar");
        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setPreferredSize(headerButton);
        addButtonPanel.add(addButton);

        JButton editButton = new JButton("Editar");
        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setPreferredSize(headerButton);
        editButtonPanel.add(editButton);

        JButton deletButton = new JButton("Deletar");
        JPanel deletButtonPanel = new JPanel();
        deletButtonPanel.setPreferredSize(headerButton);
        deletButtonPanel.add(deletButton);

        JButton devolverButton = new JButton("Devolver");
        JPanel devolverButtonPanel = new JPanel();
        devolverButtonPanel.setPreferredSize(headerButton);
        devolverButtonPanel.add(devolverButton);

        JButton emprestarButton = new JButton("Emprestar");
        JPanel emprestarButtonPanel = new JPanel();
        emprestarButtonPanel.setPreferredSize(headerButton);
        emprestarButtonPanel.add(emprestarButton);

        JButton stsButton = new JButton("Status");
        JPanel stsButtonPanel = new JPanel();
        stsButtonPanel.setPreferredSize(headerButton);
        stsButtonPanel.add(stsButton);

        JButton prazoButton = new JButton("Prazo");
        JPanel prazoButtonPanel = new JPanel();
        prazoButtonPanel.setPreferredSize(headerButton);
        prazoButtonPanel.add(prazoButton);








        JPanel headerPanel = new JPanel(new GridLayout(2,2));
        headerPanel.add(userNameTxtLabel, BorderLayout.WEST);
        headerPanel.add(exitPanel, BorderLayout.SOUTH);
        headerPanel.add(functionUserTxtLabel, BorderLayout.WEST);
        headerPanel.add(userEditPanel, BorderLayout.NORTH);

        exitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        userEditPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userEditPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        add(headerPanel, BorderLayout.NORTH);



        JPanel searchPanel = new JPanel(new GridLayout(1,3));
        searchPanel.add(fieldSearchLabel);
        searchPanel.add(optionsSearchComboBox);
        searchPanel.add(searchButtonPanel, BorderLayout.CENTER);
        add(searchPanel,BorderLayout.SOUTH);


        JTable resultSearchTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultSearchTable);

        String[] columnsTitle = {"ISBN","Título","Autor","Categoria"};
        DefaultTableModel defaultSearchTableModel = new DefaultTableModel(columnsTitle, 0);

        resultSearchTable.setModel(defaultSearchTableModel);
        add(scrollPane);

        JPanel optionsBookPanel = new JPanel(new GridLayout(1,7));
        optionsBookPanel.add(addButtonPanel);
        optionsBookPanel.add(editButtonPanel);
        optionsBookPanel.add(deletButtonPanel);
        optionsBookPanel.add(devolverButtonPanel);
        optionsBookPanel.add(emprestarButtonPanel);
        optionsBookPanel.add(stsButtonPanel);
        optionsBookPanel.add(prazoButtonPanel);
        add(optionsBookPanel, BorderLayout.SOUTH);

        pack();
    }


}