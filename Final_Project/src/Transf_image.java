import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Transf_image extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transf_image frame = new Transf_image();
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
	public Transf_image() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrHjkhjksfdafasfasfasfasfasfasfasfafasfasfa = new JTextArea();
		txtrHjkhjksfdafasfasfasfasfasfasfasfafasfasfa.setText("Etapa 1");
		txtrHjkhjksfdafasfasfasfasfasfasfasfafasfasfa.setBounds(156, 79, 109, 46);
		contentPane.add(txtrHjkhjksfdafasfasfasfasfasfasfasfafasfasfa);
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal menu = new MenuPrincipal();
				Doc doc = new Doc();
				doc.dispose();
				//doc.toBack();
				doc.setVisible(true);
				dispose();
			}
		});
		
		btnRetornar.setBounds(345, 238, 89, 23);
		contentPane.add(btnRetornar);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Na parte do código: IplImage imagem = cvLoadImage('tomejerry.png') é carregado uma imagem para ....");
		textPane.setBounds(24, 139, 344, 76);
		contentPane.add(textPane);
		
	}
}
