package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

public class FEN_DescriptionFromages extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Fromage c;
    private List<Article> articles;
    private JSpinner spinner;
    private JComboBox<String> comboBox;
    private Panier panier;
    private FEN_ListeFromages parentFrame;
    private int choix; // Stocker l'indice choisi dynamiquement

    public FEN_DescriptionFromages(int index_achat,int index_liste, List<Fromage> cheese, Panier panier, FEN_ListeFromages parentFrame, JButton bouton, boolean modification_panier, JTable table, int choix_modification_panier, JComboBox jcbb) {
        this.parentFrame = parentFrame;
        c = cheese.get(index_liste);
        articles = c.getArticles();
        this.panier = panier;

        String image = "src\\main\\resources\\images\\fromages\\hauteur200\\" + cheese.get(index_liste).getNomImage() + ".jpg";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel p_North = new JPanel();
        contentPane.add(p_North, BorderLayout.NORTH);
        p_North.setLayout(new BorderLayout(0, 0));

        JPanel p_North1 = new JPanel();
        p_North1.setBorder(UIManager.getBorder("Menu.border"));
        p_North1.setBackground(new Color(254, 224, 78));
        p_North.add(p_North1);
        p_North1.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel(cheese.get(index_liste).getDésignation());
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
        p_North1.add(lblTitle, BorderLayout.NORTH);

        JPanel p_South = new JPanel();
        contentPane.add(p_South, BorderLayout.SOUTH);
        p_South.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnValider = new JButton("Valider");
        btnValider.setForeground(new Color(255, 255, 255));
        btnValider.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        btnValider.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        btnValider.setBackground(new Color(0, 51, 102));

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setForeground(new Color(255, 255, 255));
        btnAnnuler.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
        btnAnnuler.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        quitter(btnAnnuler);
        btnAnnuler.setBackground(new Color(0, 51, 102));
        p_South.add(btnAnnuler);

        JPanel p_center = new JPanel();
        contentPane.add(p_center, BorderLayout.CENTER);
        p_center.setLayout(new BorderLayout(0, 0));

        JPanel p_Center_Est = new JPanel();
        p_center.add(p_Center_Est, BorderLayout.EAST);
        p_Center_Est.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel p_Center_South = new JPanel();
        p_center.add(p_Center_South, BorderLayout.SOUTH);

        comboBox = new JComboBox<>();
        controleurRemplirComboBox();
        p_Center_South.add(comboBox);

        spinner = new JSpinner();
        initialiserSpinner(index_achat, panier, modification_panier, choix_modification_panier);
        p_Center_South.add(spinner);

        // Ajouter un écouteur pour mettre à jour le JSpinner lorsque l'utilisateur change la sélection
        ecouteur_maj_spinner();

        controleurValider(btnValider, spinner, comboBox, bouton,modification_panier,index_achat, table, cheese, index_liste, jcbb);
        p_South.add(btnValider);

        JPanel p_Center_Center = new JPanel();
        p_center.add(p_Center_Center, BorderLayout.CENTER);
        p_Center_Center.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNewLabel_1 = new JLabel("");
        p_Center_Center.add(lblNewLabel_1);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(image));

        JTextArea textArea = new JTextArea(cheese.get(index_liste).getDescription());
        textArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(254, 224, 78), new Color(254, 224, 78)), "Description", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        p_Center_Center.add(textArea);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JPanel panel = new JPanel();
        p_center.add(panel, BorderLayout.NORTH);
    }

	private void initialiserSpinner(int index_achat, Panier panier, boolean modification_panier,
			int choix_modification_panier) {
		if (!articles.isEmpty()) {
        	if (!modification_panier) {
	            choix = comboBox.getSelectedIndex();
	            spinner.setModel(new SpinnerNumberModel(0, 0, articles.get(choix).getQuantitéEnStock(), 1));
	            
        	}else {
        		choix = choix_modification_panier;
        		comboBox.setSelectedIndex(choix);
        		Achats achat = new Achats(articles.get(choix), 0);

        		if (!panier.contient(achat)){
        			spinner.setModel(new SpinnerNumberModel(panier.getProduits().get(index_achat).getQuantité(), 0, articles.get(choix).getQuantitéEnStock()+panier.getProduits().get(index_achat).getQuantité(), 1));
        		}else {
        			spinner.setModel(new SpinnerNumberModel(panier.getProduits().get(panier.getAchats(achat)).getQuantité(), 0, articles.get(choix).getQuantitéEnStock()+panier.getProduits().get(panier.getAchats(achat)).getQuantité(), 1));
        		}
        	}
        }
	}

	public void quitter(JButton btnAnnuler) {
		btnAnnuler.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
	}

	public void ecouteur_maj_spinner() {
		comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choix = comboBox.getSelectedIndex(); // Mettre à jour l'indice choisi dynamiquement
              
                if (choix >= 0 && choix < articles.size()) {
                    
                	spinner.setModel(new SpinnerNumberModel(0, 0, articles.get(choix).getQuantitéEnStock(), 1));
                
                }
            }
        });
	}

    private void controleurRemplirComboBox() {
        if (!(c instanceof FromageALUnité)) {
            for (Article a : articles) {
                comboBox.addItem(a.getClé() + " : " + a.getPrixTTC() + "€ prix TTC");
            }
        } else {
            comboBox.addItem("A l'unité : " + articles.get(0).getPrixTTC() + "€ prix TTC");
        }
    }

    private void controleurValider(JButton btnValider, JSpinner spinner, JComboBox comboBox, JButton bouton, boolean modification_panier, int index_achat, JTable table,List<Fromage> cheese, int index_liste, JComboBox jcbb) {
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int quantite = (int) spinner.getValue();
            	Achats achat = new Achats(articles.get(choix), quantite);
                        	
            	if (!modification_panier) {
	                panier.ajouterAchat(achat, quantite);
		
	                spinner.setModel(new SpinnerNumberModel(0, 0, articles.get(choix).getQuantitéEnStock(), 1));
	                JOptionPane.showMessageDialog(null, "Fromage ajouté au panier!");
	                parentFrame.updatePrixPanier(FEN_DescriptionFromages.this.panier, bouton);
	                dispose();
            	}else {
            		
	            	if (panier.contient(achat)) {
	                                
                        if (quantite == 0) {
                        	if (JOptionPane.showInternalConfirmDialog(null,"Voulez vous supprimer cet article ?") == 0) {
                        		panier.supprimerAchat(panier.getProduits().get(index_achat));
                        		parentFrame.updatePrixPanier(FEN_DescriptionFromages.this.panier, bouton);
                        		dispose();
                        	}
                        }
                        //quand la quantité est differente de 0                  
                        else {
                        	
                        	if (quantite < panier.getProduits().get(panier.getAchats(achat)).getQuantité()) {
                        
                        	modifierPanier(panier.getProduits().get(index_achat),achat,quantite, bouton);
	            			spinner.setModel(new SpinnerNumberModel(quantite, 0, articles.get(choix).getQuantitéEnStock()+quantite, 1));
	            				                        
	                        }else {
	                        	modifierPanier(panier.getProduits().get(index_achat),achat,quantite, bouton);
	                        	
		                        spinner.setModel(new SpinnerNumberModel(panier.getProduits().get(panier.getAchats(achat)).getQuantité(), 0, articles.get(choix).getQuantitéEnStock()+panier.getProduits().get(panier.getAchats(achat)).getQuantité(), 1));
		                        
	                        }
                        	JOptionPane.showMessageDialog(null, "Article modifiée");
                        	dispose();
                        }
            		
                    //si la clé de l'article est modifié
	            	}else {
	            		if (quantite == 0) {
                        	if (JOptionPane.showInternalConfirmDialog(null,"Voulez vous supprimer cet article ?") == 0) {
                        		panier.supprimerAchat(panier.getProduits().get(index_achat));
                        		parentFrame.updatePrixPanier(FEN_DescriptionFromages.this.panier, bouton);
                        	}
                        } else {
                        	modifierPanier(panier.getProduits().get(index_achat),achat,quantite, bouton);
                        }
	            	}
            		FEN_Panier.updatePrixPanier2(panier, table, index_achat, cheese,jcbb);
            	}
            }
        });
    }
    
    private void modifierPanier(Achats ancienAchat, Achats nouvelAchat, int q, JButton bouton) {
    	panier.supprimerAchat(ancienAchat);
		panier.ajouterAchat(nouvelAchat, q);
		parentFrame.updatePrixPanier(FEN_DescriptionFromages.this.panier, bouton);
    }
}