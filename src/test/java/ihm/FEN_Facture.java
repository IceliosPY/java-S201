package ihm;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modele.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class FEN_Facture extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableRecapAchats;
    private Client client;


    /**
     * Create the frame.
     */
    public FEN_Facture(Panier panier, Client client, Transport t) {
    	this.setMinimumSize(new Dimension(500, 700));
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        this.setMinimumSize(new Dimension(600, 400));
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));


        JPanel p_North = new JPanel();
        contentPane.add(p_North, BorderLayout.NORTH);
        p_North.setLayout(new BorderLayout());

        JLabel lblTitreFacture = new JLabel("Facture");
        lblTitreFacture.setOpaque(true);
        lblTitreFacture.setBackground(new Color(254, 224, 78));
        lblTitreFacture.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        lblTitreFacture.setHorizontalAlignment(SwingConstants.CENTER);
        p_North.add(lblTitreFacture, BorderLayout.CENTER);


        JPanel p_Center = new JPanel();
        contentPane.add(p_Center, BorderLayout.CENTER);
        p_Center.setLayout(new BorderLayout(10, 10));

        JPanel p_Center_North = new JPanel();
        p_Center.add(p_Center_North, BorderLayout.NORTH);
        p_Center_North.setLayout(new GridLayout(8, 1, 0, 5));

        JLabel lblDate = new JLabel("Commandé le " + new java.text.SimpleDateFormat("dd MMMM yyyy", java.util.Locale.FRENCH).format(new java.util.Date())); 
        lblDate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
        p_Center_North.add(lblDate);

        JLabel lblIdentite = new JLabel(this.client.getNom() + " " + client.getPrénom());
        lblIdentite.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblIdentite);

        JLabel lblAdresse = new JLabel(this.client.getAdresse1());
        lblAdresse.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblAdresse);

        JLabel lblAdresse2 = new JLabel(this.client.getAdresse2());
        lblAdresse2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblAdresse2);
        
        JLabel lblVille = new JLabel(this.client.getVille());
        lblVille.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblAdresse2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblVille);
        
        JLabel lblCP = new JLabel(this.client.getCodePostal());
        lblCP.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblCP);

        JLabel lblTel = new JLabel(this.client.getTéléphone());
        lblTel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblTel);

        JLabel lblMail = new JLabel(this.client.getMail());
        lblMail.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        p_Center_North.add(lblMail);

        
        JPanel p_Center_Center = new JPanel();
        p_Center.add(p_Center_Center, BorderLayout.CENTER);
        p_Center_Center.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        p_Center_Center.add(scrollPane, BorderLayout.CENTER);

        tableRecapAchats = new JTable();
        tableRecapAchats.setMaximumSize(new Dimension(0, 5));
        tableRecapAchats.setGridColor(Color.BLACK);
        tableRecapAchats.setRowSelectionAllowed(false);
        tableRecapAchats.setFont(new Font("Yu Gothic Light", Font.PLAIN, 13));
        tableRecapAchats.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Fromage", "Prix Unitaire", "Quantité", "Type Vente", "Prix TTC"}
        ));
        tableRecapAchats.setRowHeight(60);

        // Remplir la table
        for (Achats a : panier.getProduits()) {
            DefaultTableModel model = (DefaultTableModel) tableRecapAchats.getModel();
            String nom = a.getArticle().getFromage().getDésignation();
            int quantite = a.getQuantité();
            String type_vente = a.getArticle().getClé();
            if (a.getArticle().getFromage() instanceof FromageALUnité) {
                type_vente = "A l'unité";
            }
            float prix_unitaire = a.getArticle().getPrixTTC();
            float prix_total = a.prix();
            model.addRow(new Object[]{nom, prix_unitaire, quantite, type_vente, prix_total});
        }

        scrollPane.setViewportView(tableRecapAchats);


        JPanel p_Center_South = new JPanel();
        p_Center_South.setLayout(new GridLayout(1, 4, 10, 10));

        JLabel lblFraisLivraison = new JLabel("Frais de livraison");
        lblFraisLivraison.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblFraisLivraison.setHorizontalAlignment(SwingConstants.RIGHT);
        p_Center_South.add(lblFraisLivraison);

        float fraisLivraison = panier.calculerFraisDePort(t); 
        JLabel lblFraisLivraisonValue = new JLabel(String.format("%.2f€", fraisLivraison));
        lblFraisLivraisonValue.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblFraisLivraisonValue.setHorizontalAlignment(SwingConstants.LEFT);
        p_Center_South.add(lblFraisLivraisonValue);

        JLabel lblPrixTotal = new JLabel("Prix Total");
        lblPrixTotal.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lblPrixTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        p_Center_South.add(lblPrixTotal);

        float total = panier.getPrixTotal();
        float prixTotal = total + fraisLivraison;
        JLabel lblPrixTotalValue = new JLabel(String.format("%.2f€", prixTotal));
        lblPrixTotalValue.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        lblPrixTotalValue.setHorizontalAlignment(SwingConstants.LEFT);
        p_Center_South.add(lblPrixTotalValue);

        p_Center.add(p_Center_South, BorderLayout.SOUTH);
        
        JPanel p_South = new JPanel();
        contentPane.add(p_South, BorderLayout.SOUTH);
        p_South.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton btnImprimer = new JButton("Imprimer");
        btnImprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel yourComponent = p_Center;
                PrinterJob pjob = PrinterJob.getPrinterJob();
                PageFormat preformat = pjob.defaultPage();
                preformat.setOrientation(PageFormat.LANDSCAPE);
                PageFormat postformat = pjob.pageDialog(preformat);
                if (preformat != postformat) {
                    pjob.setPrintable(new Printer(yourComponent), postformat);
                    if (pjob.printDialog()) {
                        try {
                            pjob.print();
                        } catch (PrinterException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        btnImprimer.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnImprimer.setBackground(new Color(0, 51, 102));
        btnImprimer.setForeground(Color.WHITE);
        p_South.add(btnImprimer);

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnQuitter.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnQuitter.setBackground(new Color(0, 51, 102));
        btnQuitter.setForeground(Color.WHITE);
        p_South.add(btnQuitter);

        
    }
}
