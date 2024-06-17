package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class FEN_Coordonnees extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextField textField_prénom;
    private JTextField textField_nom;
    private JTextField textField_adresse_1;
    private JTextField textField_adresse_2;
    private JTextField textField_code_postal;
    private JTextField textField_Ville;
    private JTextField textField_Téléphone;
    private JTextField textField_Mail;
    private Client client;
	protected Panier panier;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();


    /**
     * Launch the application.
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FEN_Coordonnees frame = new FEN_Coordonnees();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     */

    /**
     * Create the frame.
     */
    public FEN_Coordonnees(Panier panier, Transport transport) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        this.panier = panier;
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(254, 224, 78));
        contentPane.add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblHeader = new JLabel("Vos coordonnées");
        lblHeader.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(lblHeader);
        
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 6));
        
        JPanel paymentPanel = new JPanel();
        paymentPanel.setBorder(new TitledBorder(new LineBorder(new Color(254, 224, 78), 2), "Moyen de paiement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paymentPanel.setBackground(SystemColor.control);
        panel_2.add(paymentPanel, BorderLayout.NORTH);
        paymentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        
        JRadioButton rdbtnCreditCard = new JRadioButton("Carte de crédit");
        rdbtnCreditCard.setSelected(true);
        buttonGroup.add(rdbtnCreditCard);
        paymentPanel.add(rdbtnCreditCard);
        
        JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
        buttonGroup.add(rdbtnPaypal);
        paymentPanel.add(rdbtnPaypal);
        
        JPanel newsletterPanel = new JPanel();
        newsletterPanel.setBorder(new TitledBorder(new LineBorder(new Color(254, 224, 78), 2), "Abonnement \u00E0 notre NewsLetter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        newsletterPanel.setBackground(SystemColor.control);
        panel_2.add(newsletterPanel, BorderLayout.SOUTH);
        newsletterPanel.setLayout(new GridLayout(0, 2, 10, 10));
        
        JRadioButton rdbtnOui = new JRadioButton("Oui");
        buttonGroup_1.add(rdbtnOui);
        rdbtnOui.setSelected(true);
        newsletterPanel.add(rdbtnOui);
        
        JRadioButton rdbtnNon = new JRadioButton("Non");
        buttonGroup_1.add(rdbtnNon);
        newsletterPanel.add(rdbtnNon);
        
        JPanel formPanel = new JPanel();
        panel_1.add(formPanel);
        formPanel.setLayout(new GridLayout(8, 3, 20, 10));
        
        JLabel image_nom = new JLabel("");
        image_nom.setHorizontalAlignment(SwingConstants.CENTER);
        image_nom.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\utilisateur.png", 30, 30));
        
        formPanel.add(image_nom);
        
        JLabel lbl_nom = new JLabel("Nom");
        formPanel.add(lbl_nom);
        
        textField_nom = new JTextField();
        textField_nom.setColumns(10);
        formPanel.add(textField_nom);
        
        JLabel image_prénom = new JLabel("");
        image_prénom.setHorizontalAlignment(SwingConstants.CENTER);
        image_prénom.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\utilisateur.png",  30, 30));
        formPanel.add(image_prénom);
        
        JLabel lbl_prénom = new JLabel("Prénom");
        formPanel.add(lbl_prénom);
        
        textField_prénom = new JTextField();
        textField_prénom.setColumns(1);
        formPanel.add(textField_prénom);
        
        JLabel image_Adresse_1 = new JLabel("");
        image_Adresse_1.setHorizontalAlignment(SwingConstants.CENTER);
        image_Adresse_1.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\adresse-du-domicile.png", 30, 30));
        formPanel.add(image_Adresse_1);
        
        JLabel lbl_Adresse_1 = new JLabel("Adresse 1");
        formPanel.add(lbl_Adresse_1);
        
        textField_adresse_1 = new JTextField();
        textField_adresse_1.setColumns(1);
        formPanel.add(textField_adresse_1);
        
        JLabel image_Adresse_2 = new JLabel("");
        image_Adresse_2.setHorizontalAlignment(SwingConstants.CENTER);
        image_Adresse_2.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\adresse-du-domicile.png", 30, 30));
        formPanel.add(image_Adresse_2);
        
        JLabel lbl_Adresse_2 = new JLabel("Adresse 2");
        formPanel.add(lbl_Adresse_2);
        
        textField_adresse_2 = new JTextField();
        textField_adresse_2.setColumns(1);
        formPanel.add(textField_adresse_2);
        
        JLabel image_Code_Postal = new JLabel("");
        image_Code_Postal.setHorizontalAlignment(SwingConstants.CENTER);
        image_Code_Postal.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\trouver.png", 30, 30));
        formPanel.add(image_Code_Postal);
        
        JLabel lbl_Code_Postal = new JLabel("Code Postal");
        formPanel.add(lbl_Code_Postal);
        
        textField_code_postal = new JTextField();
        textField_code_postal.setColumns(1);
        formPanel.add(textField_code_postal);
        
        JLabel image_Ville = new JLabel("");
        image_Ville.setHorizontalAlignment(SwingConstants.CENTER);
        image_Ville.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\ville.png", 30, 30));
        formPanel.add(image_Ville);
        
        JLabel lbl_Ville = new JLabel("Ville");
        formPanel.add(lbl_Ville);
        
        textField_Ville = new JTextField();
        textField_Ville.setColumns(1);
        formPanel.add(textField_Ville);
        
        JLabel image_Téléphone = new JLabel("");
        image_Téléphone.setHorizontalAlignment(SwingConstants.CENTER);
        image_Téléphone.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\telephone-intelligent.png", 30, 30));
        formPanel.add(image_Téléphone);
        
        JLabel lbl_Téléphone = new JLabel("Téléphone");
        formPanel.add(lbl_Téléphone);
        
        textField_Téléphone = new JTextField();
        textField_Téléphone.setColumns(1);
        formPanel.add(textField_Téléphone);
        
        JLabel image_Mail = new JLabel("");
        image_Mail.setHorizontalAlignment(SwingConstants.CENTER);
        image_Mail.setIcon(resizeIcon("src\\main\\resources\\images\\icones\\e-mail.png", 30, 30));
        formPanel.add(image_Mail);
        
        JLabel lbl_Mail = new JLabel("Mail");
        formPanel.add(lbl_Mail);
        
        textField_Mail = new JTextField();
        textField_Mail.setColumns(1);
        formPanel.add(textField_Mail);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton btnOk = new JButton("OK");
        btnOk.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        btnOk.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnOk.setBackground(new Color(0, 51, 102));
        btnOk.setForeground(new Color(255, 255, 255));
        controleurBtnOk(btnOk, transport);
        
        panel.add(btnOk);
        
        JButton btnCancel = new JButton("Annuler");
        btnCancel.setBorder(new CompoundBorder(new LineBorder(Color.WHITE,2), new EmptyBorder(2, 10, 2, 10)));
        btnCancel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        controleurBtnCancel(btnCancel);
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.setBackground(new Color(0, 51, 102));
        panel.add(btnCancel);
    }

	private void controleurBtnCancel(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
	}

	private void controleurBtnOk(JButton btnOk, Transport transport) {
		btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mettre à jour les informations du client avec les données saisies
                client = new Client(
                	textField_nom.getText(),
                	textField_prénom.getText(),
                	textField_adresse_1.getText(),
                	textField_adresse_2.getText(),
                    textField_code_postal.getText(),
                    textField_Ville.getText(),
                    textField_Téléphone.getText(),
                    textField_Mail.getText()
                );
                
                
                if (client.informationsCompletes()) {
                    FEN_Facture f = new FEN_Facture(FEN_Coordonnees.this.panier, client, transport);
                    f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    f.setVisible(true);
                }
                else {
                	System.out.println("incomplet");
                	JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs");
                }
               
            }
        });
	}
    
    public ImageIcon resizeIcon(String path, int width, int height) {
        // Charger l'image à partir du chemin
        ImageIcon icon = new ImageIcon(path);
        // Redimensionner l'image
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Créer une nouvelle ImageIcon à partir de l'image redimensionnée
        return new ImageIcon(img);
    }

    

}
