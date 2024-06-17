package ihm;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.*;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FEN_Panier extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTable table;
    private static JTextField textField_SousTotal;
    private static JTextField textField_FraisTransport;
    private static JTextField textField_Total;
    private static float fraisDePort;
    private Panier panier;
    

    public FEN_Panier(List<Fromage> cheese,Panier panier,FEN_ListeFromages parentFrame,JButton bouton) {
        this.panier = panier;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 5));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(254, 224, 78)); // #FEE04E
        headerPanel.setBorder(null);
        contentPane.add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout(10, 10));

        JLabel lblVotrePanier = new JLabel("   Votre panier");
        lblVotrePanier.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblVotrePanier.setForeground(new Color(0, 0, 0));
        headerPanel.add(lblVotrePanier, BorderLayout.WEST);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        contentPane.add(tablePanel, BorderLayout.CENTER);

        table = new JTable() {
        	//permettre d'afficher les images dans une JTable
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
            }
            public boolean isCellEditable(int row, int column) {                
                return false;               
        };
        };
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Image", "Nom fromage", "Type vente", "Quantit\u00E9", "Prix unitaire", "Prix total", "index", "choix"
        	}
        ));
        table.removeColumn(table.getColumnModel().getColumn(7));
        table.removeColumn(table.getColumnModel().getColumn(6));
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
       
        table.setRowHeight(65);

        remplirTable(cheese, this.panier);

        table.setRowHeight(30);
        table.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout(10, 10));
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        JPanel transportPanel = new JPanel();
        transportPanel.setLayout(new BorderLayout(10, 10));
        footerPanel.add(transportPanel, BorderLayout.CENTER);

        JLabel lblTransport = new JLabel("Frais de ports gratuit à partir de 120€");
        lblTransport.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        transportPanel.add(lblTransport, BorderLayout.NORTH);

        JPanel transportOptionsPanel = new JPanel();
        transportOptionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        transportPanel.add(transportOptionsPanel, BorderLayout.CENTER);

        JLabel lblIcon = new JLabel();
        ImageIcon iconeOriginale = new ImageIcon("src\\main\\resources\\images\\logo_transport\\Colissimo.jpg");
        Image imageRedimensionnee = iconeOriginale.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        lblIcon.setIcon(new ImageIcon(imageRedimensionnee));
        transportOptionsPanel.add(lblIcon);


        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Colissimo", "Chronorelais", "Chronofresh"}));
        comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        transportOptionsPanel.add(comboBox);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(3, 2, 10, 10));
        footerPanel.add(totalPanel, BorderLayout.EAST);

        JLabel lblSousTotal = new JLabel("Sous-total");
        lblSousTotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        totalPanel.add(lblSousTotal);

        textField_SousTotal = new JTextField();
        textField_SousTotal.setEditable(false);
        textField_SousTotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        textField_SousTotal.setBackground(new Color(245, 245, 245));
        textField_SousTotal.setForeground(new Color(0, 0, 0));
        totalPanel.add(textField_SousTotal);

        
        JLabel lblFraisLivraison = new JLabel("Frais de livraison");
        lblFraisLivraison.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        totalPanel.add(lblFraisLivraison);

        textField_FraisTransport = new JTextField();
        textField_FraisTransport.setEditable(false);
        textField_FraisTransport.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        textField_FraisTransport.setBackground(new Color(245, 245, 245));
        textField_FraisTransport.setForeground(new Color(0, 0, 0));
        totalPanel.add(textField_FraisTransport);
        
       
        JLabel lblTotal = new JLabel("Total");
        lblTotal.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
        totalPanel.add(lblTotal);

        textField_Total = new JTextField();
        textField_Total.setEditable(false);
        textField_Total.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        textField_Total.setBackground(new Color(245, 245, 245));
        textField_Total.setForeground(new Color(0, 0, 0));
        totalPanel.add(textField_Total);
                
        updateTotaux(comboBox,panier);
        
        controleurComboBox(comboBox, panier, lblIcon);
        selectionLigneTable(cheese, panier, parentFrame, bouton, comboBox);
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.add(actionPanel, BorderLayout.SOUTH);

        JButton btnValider = new JButton("Valider le panier");
		TypeTransporteur t = TypeTransporteur.getTypeTransporteur((String) comboBox.getSelectedItem());
        Transport transport = new Transport(t);
        controleurBtnValider(btnValider, transport);
        btnValider.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnValider.setForeground(Color.WHITE);
        btnValider.setBackground(new Color(0, 51, 102)); // #003366
        btnValider.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        actionPanel.add(btnValider);

        JButton btnVider = new JButton("Vider le panier");
        bouton_vider_panier(bouton, parentFrame, btnVider, comboBox);
        btnVider.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnVider.setForeground(Color.WHITE);
        btnVider.setBackground(new Color(0, 51, 102)); // #003366
        btnVider.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        actionPanel.add(btnVider);

        JButton btnContinuer = new JButton("Continuer les achats");
        controleurBtnContinuer(btnContinuer);
        btnContinuer.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnContinuer.setForeground(Color.WHITE);
        btnContinuer.setBackground(new Color(0, 51, 102)); // #003366
        btnContinuer.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        actionPanel.add(btnContinuer);

        
    }

	private static void remplirTable(List<Fromage> cheese, Panier p) {
		for (Achats a : p.getProduits()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String imagePath = "src/main/resources/images/fromages/hauteur40/" + a.getArticle().getFromage().getNomImage() + ".jpg";
            ImageIcon image = new ImageIcon(imagePath);
            String nom = a.getArticle().getFromage().getDésignation();
            int index_2 = cheese.indexOf(a.getArticle().getFromage());
            int choix = cheese.get(index_2).getArticles().indexOf(a.getArticle());
            
            String type_vente = a.getArticle().getClé();
            if (a.getArticle().getFromage() instanceof FromageALUnité) {
                type_vente = "A l'unité";
            }
            int quantite = a.getQuantité();
            
            String prix_unitaire = String.format("%.2f", a.getArticle().getPrixTTC());
            String prix_total = String.format("%.2f", a.prix());
            model.addRow(new Object[]{image, nom,type_vente, quantite, prix_unitaire, prix_total,index_2, choix});
        }
	}

	private void controleurBtnContinuer(JButton btnContinuer) {
		btnContinuer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
	}

	private void selectionLigneTable(List<Fromage> cheese, Panier panier, FEN_ListeFromages parentFrame, JButton bouton,
			JComboBox<String> comboBox) {
		table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if (table.getSelectedRow()>= 0) {
        		int index = (int)table.getModel().getValueAt(table.getSelectedRow(),6);

        		int choix = (int)table.getModel().getValueAt(table.getSelectedRow(),7);
        		
        		FEN_DescriptionFromages f = new FEN_DescriptionFromages(table.getSelectedRow(),index,cheese,panier, parentFrame, bouton,true, table,choix, comboBox);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
								
        		}
        	}
        });
	}

	private void controleurBtnValider(JButton btnValider, Transport transport) {
		btnValider.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FEN_Panier.this.panier.isEmpty()) {
        			FEN_Coordonnees f = new FEN_Coordonnees(FEN_Panier.this.panier, transport);
    				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    				f.setVisible(true);
        		}
        		
        	}
        });
	}

	public void bouton_vider_panier(JButton bouton, FEN_ListeFromages parentFrame, JButton btnVider, JComboBox comboBox) {
		btnVider.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!panier.isEmpty()) {
        			if (JOptionPane.showInternalConfirmDialog(null,"Est-ce que vous souhaitez vider le panier ?") == 0) {
                		FEN_Panier.this.panier.viderPanier();
                		((DefaultTableModel) table.getModel()).setRowCount(0);
                		parentFrame.updatePrixPanier(FEN_Panier.this.panier, bouton);
                		updateTotaux(comboBox, panier);
                	}
        		}
        		
        	}
        });
	}
	
	private static void controleurComboBox(JComboBox<String> comboBox, Panier panier,JLabel lblIcon) {
		comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	
                	updateTotaux(comboBox, panier);
                    
                    //changer l'icone
                	TypeTransporteur t = TypeTransporteur.getTypeTransporteur((String) comboBox.getSelectedItem());
                    ImageIcon iconeOriginale = new ImageIcon("src\\main\\resources\\images\\logo_transport\\"+ t.getNomImage());
                    Image imageRedimensionnee = iconeOriginale.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
                    lblIcon.setIcon(new ImageIcon(imageRedimensionnee));
                }
            }
        });
	}
	
	public static void updatePrixPanier2(Panier p, JTable table, int index, List<Fromage> cheese, JComboBox comboBox){
		
		((DefaultTableModel) table.getModel()).setRowCount(0);
		p.calculerPrixPanier();
		
		remplirTable(cheese, p);
		
		updateTotaux(comboBox, p);
		
    }
	

	
	public static void updateTotaux(JComboBox comboBox, Panier p) {
		float sous_total = p.getPrixTotal();
		textField_SousTotal.setText(String.format("%.2f", sous_total) + "€");
		
		TypeTransporteur t = TypeTransporteur.getTypeTransporteur((String) comboBox.getSelectedItem());
        Transport transport = new Transport(t);
        float fraisDePort = p.calculerFraisDePort(transport);
        textField_FraisTransport.setText(String.format("%.2f", fraisDePort) + "€");   
        
        textField_Total.setText(String.format("%.2f",(sous_total + fraisDePort)) + "€");
        
        
        
	}
}
