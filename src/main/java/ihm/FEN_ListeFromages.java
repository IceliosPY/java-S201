package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.*;


import java.awt.BorderLayout;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;

public class FEN_ListeFromages extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JButton Btn_panier;
	private JPanel contentPane;
	private Fromages Allcheese = modele.GenerationFromages.générationBaseFromages();
	private List<Fromage> cheese;
	private Panier panier;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FEN_ListeFromages frame = new FEN_ListeFromages();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FEN_ListeFromages() {
		int choix = 0;
		cheese = Allcheese.getFromages();
		List<String> values = new LinkedList<>();
		this.panier = new Panier();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel p_North = new JPanel();
		p_North.setOpaque(false);
		p_North.setBackground(new Color(254, 224, 78));
		contentPane.add(p_North, BorderLayout.NORTH);
		p_North.setLayout(new BorderLayout(0, 0));
		
		JPanel p1_North = new JPanel();
		p1_North.setBackground(new Color(254, 224, 78));
		p_North.add(p1_North, BorderLayout.CENTER);
		
		JLabel North_Image = new JLabel("");
		North_Image.setIcon(new ImageIcon("src\\main\\resources\\images\\icones\\fromage_1.png"));
		p1_North.add(North_Image);
		
		JLabel lblNewLabel = new JLabel("Nos fromages");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		p1_North.add(lblNewLabel);
		
		JPanel p2_North = new JPanel();
		p2_North.setBackground(new Color(254, 224, 78));
		p_North.add(p2_North, BorderLayout.EAST);
		JList<String> listFromages = new JList<>();
		JButton Btn_panier = new JButton("");
		Btn_panier.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		Btn_panier.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
		controleurOuvrirPanier(listFromages,Btn_panier,choix);
		Btn_panier.setBackground(new Color(255, 250, 205));
		
		Btn_panier.setIconTextGap(5);
		
		this.panier.calculerPrixPanier();
		Btn_panier.setText(this.panier.getPrixTotal()+"€");
		
		// Redimensionner l'image de l'icône
		ImageIcon originalIcon = new ImageIcon("src\\main\\resources\\images\\icones\\valider_panier.png");
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Ajustez la taille selon vos besoins
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		Btn_panier.setIcon(resizedIcon);
		p2_North.add(Btn_panier);
		
		
        create_values(values, listFromages);
        controleurAfficherDescription(listFromages,Btn_panier, choix);
		JScrollPane scrollPane = new JScrollPane(listFromages);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		JPanel p_South = new JPanel();
		p_South.setBorder(null);
		contentPane.add(p_South, BorderLayout.SOUTH);
		p_South.setLayout(new BorderLayout(20, 0));
		
		JPanel p1_South = new JPanel();
		p1_South.setBorder(new TitledBorder(new LineBorder(new Color(254, 224, 78), 3, true), "Filtres", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		p_South.add(p1_South, BorderLayout.CENTER);
		
		JComboBox comboBoxTypeLait = new JComboBox();
		comboBoxTypeLait.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
		comboBoxTypeLait.setBackground(new Color(255, 255, 255));
		comboBoxTypeLait.setModel(new DefaultComboBoxModel(new String[] {"Tous les fromages", "Vache", "Brebis", "Chèvre"}));
		trierFromages(listFromages, comboBoxTypeLait, values);
		
		
		p1_South.add(comboBoxTypeLait);
		
		JPanel p2_South = new JPanel();
		p_South.add(p2_South, BorderLayout.EAST);
		
		JButton btnFermer = new JButton("Quitter");
		btnFermer.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(2, 10, 2, 10)));
		btnFermer.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btnFermer.setForeground(new Color(255, 255, 255));
		btnFermer.setBackground(new Color(0, 51, 102));
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		p2_South.setLayout(new BorderLayout(0, 0));
		
		p2_South.add(btnFermer, BorderLayout.SOUTH);
		
		
	}

	private void controleurOuvrirPanier(JList<String> listFromages,JButton Btn_panier, int choix) {
		Btn_panier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FEN_Panier f = new FEN_Panier(cheese ,FEN_ListeFromages.this.panier,FEN_ListeFromages.this, Btn_panier);
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	public void controleurAfficherDescription(JList<String> listFromages, JButton Btn_panier, int choix) {
		listFromages.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
				FEN_DescriptionFromages f = new FEN_DescriptionFromages(0,listFromages.getSelectedIndex(),cheese,FEN_ListeFromages.this.panier, FEN_ListeFromages.this, Btn_panier, false, new JTable(),choix, new JComboBox());
				f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	private void create_values(List<String> values, JList<String> listFromages) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Fromage c : cheese) {
            listModel.addElement(c.getDésignation());
        }
        listFromages.setModel(listModel);
	}

	private void trierFromages(JList<String> listFromages, JComboBox comboBoxTypeLait, List<String> values) {
		comboBoxTypeLait.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxTypeLait.getSelectedItem()!="Tous les fromages") {
					
					cheese = Allcheese.fromagesAuLaitDe(TypeLait.getTypeLait((String) comboBoxTypeLait.getSelectedItem()));
					values.removeAll(values);
					
					listFromages.removeAll();
					
					create_values(values, listFromages);
					
				}else {
					cheese = Allcheese.getFromages();
					values.removeAll(values);
					listFromages.removeAll();
					create_values(values, listFromages);
				}
			}
		});
	
	}
	
	public void updatePrixPanier(Panier p, JButton bouton){
		p.calculerPrixPanier();
		bouton.setText(String.format("%.2f", p.getPrixTotal()) + " €");
    }
	
}
