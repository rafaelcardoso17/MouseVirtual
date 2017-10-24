import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Doc extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doc frame = new Doc();
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
	public Doc() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bruna\\Documents\\Rafael\\1.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPane.add(menuBar);
		
		JMenu mnEtapa = new JMenu("Etapa 1");
		menuBar.add(mnEtapa);
		
		JMenuItem mntmTransformaesDeImagem = new JMenuItem("Transforma\u00E7\u00F5es de imagem");
		mntmTransformaesDeImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transf_image trans = new Transf_image();
				trans.setVisible(true);
				dispose();
			}
		});
		mnEtapa.add(mntmTransformaesDeImagem);
		
		JMenu mnEtapa_1 = new JMenu("Etapa 2");
		menuBar.add(mnEtapa_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Processamento de v\u00EDdeo");
		mnEtapa_1.add(mntmNewMenuItem);
		
		JMenu mnEtapa_2 = new JMenu("Etapa 3");
		menuBar.add(mnEtapa_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem(" Grava\u00E7\u00E3o de v\u00EDdeo");
		mnEtapa_2.add(mntmNewMenuItem_1);
		
		JMenu mnEtapa_3 = new JMenu("Etapa 4");
		menuBar.add(mnEtapa_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Filtragem de cores");
		mnEtapa_3.add(mntmNewMenuItem_2);
		
		JMenu mnEtapa_4 = new JMenu("Etapa 5");
		menuBar.add(mnEtapa_4);
		
		JMenuItem mntmControleDeFiltragem = new JMenuItem("Controle de Filtragem");
		mnEtapa_4.add(mntmControleDeFiltragem);
		
		JMenu mnEtapa_5 = new JMenu("Etapa 6");
		menuBar.add(mnEtapa_5);
		
		JMenuItem mntmMomentos = new JMenuItem("Rastreamento");
		mnEtapa_5.add(mntmMomentos);
		
		JMenu mnProjetoFinal = new JMenu("Projeto Final");
		menuBar.add(mnProjetoFinal);
		
		JMenuItem mntmMouseVirtual = new JMenuItem("Mouse Virtual");
		mnProjetoFinal.add(mntmMouseVirtual);
		
		
		JButton btnRetornar = new JButton("Menu Principal");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal menu = new MenuPrincipal();
				Doc doc = new Doc();
				menu.dispose();
				//doc.toBack();
				menu.setVisible(true);
				dispose();
				
			}
		});
		
		btnRetornar.setBounds(277, 238, 157, 23);
		contentPane.add(btnRetornar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Bruna\\Documents\\Rafael\\doc.jpg"));
		lblNewLabel.setBounds(0, 22, 434, 239);
		contentPane.add(lblNewLabel);
	}
}
