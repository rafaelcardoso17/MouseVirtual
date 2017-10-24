import java.awt.AWTException;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.CvArr;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacv.cpp.opencv_imgproc.CvMoments;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MenuPrincipal extends JFrame {
	

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bruna\\Documents\\Rafael\\Icone Principal.jpg"));
		
		this.setTitle("Virtual Mouse");
		int w=640,h=480;
		CvCapture capture1;
		capture1= cvCreateCameraCapture(CV_CAP_ANY);
		cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_WIDTH,w);
		cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_HEIGHT,h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		setContentPane(contentPane);
		
		
		JButton btnEtapa1 = new JButton("Etapa 1 - Básico de transformações de imagem");
		btnEtapa1.setBounds(10, 0, 312, 23);
		
		btnEtapa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuPrincipal menu = new MenuPrincipal();
				menu.dispose();
				
				IplImage imagem = cvLoadImage("tomejerry.png");
				CvMemStorage storage = CvMemStorage.create();
				
				IplImage x = cvCreateImage(cvGetSize(imagem),IPL_DEPTH_8U,3);
				IplImage y = cvCreateImage(cvGetSize(imagem),IPL_DEPTH_8U,1);
				
				cvCvtColor(imagem,x,CV_BGR2HSV);
				cvCvtColor(imagem,y,CV_BGR2GRAY);
				
				cvShowImage("Original",imagem);
				cvShowImage("HSV",x);
				cvShowImage("GRAY",y);
				cvWaitKey();
				//cvSaveImage("Original.jpg",imagem);
				//cvSaveImage("HSV.jpg",x);
				//cvSaveImage("GRAY.jpg",y);
				cvReleaseImage(imagem);
				cvReleaseImage(x);
				cvReleaseImage(y);
				
			
			}
		});
		
		
		JButton btnEtapa2 = new JButton("Etapa2 - Processamento de vídeo ");
		btnEtapa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CvCapture capture = cvCreateCameraCapture(CV_CAP_ANY); 
				IplImage frame;
				IplImage grayimg = cvCreateImage(cvSize(640,480),IPL_DEPTH_8U,1);
				
				cvNamedWindow("Video",CV_WINDOW_AUTOSIZE);
				
				for(;;)
				{
					frame = cvQueryFrame(capture);
					if(frame == null) 
						{
							System.out.println("ERROR: NO Video File");	
							break;
						}
					
					cvCvtColor(frame,grayimg,CV_BGR2GRAY);
					cvShowImage("Video",grayimg);
					char c = (char) cvWaitKey(15);
					if(c=='q'){
						cvDestroyWindow("Video");
						return ;
					}
				}

				cvReleaseCapture(capture);
				cvDestroyWindow("Video");
			}
		});
		btnEtapa2.setBounds(10, 34, 312, 23);
		
		JButton btnEtapa3 = new JButton("Etapa3 - Gravação de vídeo");
		btnEtapa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
				cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_WIDTH,640);
				cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_HEIGHT,480);
				
				cvNamedWindow("LiveVid",CV_WINDOW_AUTOSIZE);
				
				FrameRecorder recorder1 = new OpenCVFrameRecorder("RecordVid.avi",640,480);
				recorder1.setVideoCodec(CV_FOURCC('M','J','P','G'));
				recorder1.setFrameRate(15);
				recorder1.setPixelFormat(1);
				
				try {
					recorder1.start();
				} catch (com.googlecode.javacv.FrameRecorder.Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				IplImage img1;
				
				for(;;){
					
					img1 = cvQueryFrame(capture1);
					
					if(img1==null) break;
					
					cvShowImage("LiveVid",img1);
					try {
						recorder1.record(img1);
					} catch (com.googlecode.javacv.FrameRecorder.Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					char c = (char) cvWaitKey(15);
					if(c=='q'){
						cvDestroyWindow("LiveVid");
						return ;
					}
					
				}
				
				try {
					recorder1.stop();
				} catch (com.googlecode.javacv.FrameRecorder.Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cvDestroyWindow("LiveVid");
				cvReleaseCapture(capture1);
			}
		});
		btnEtapa3.setBounds(10, 68, 312, 23);
		
		JButton btnEtapa4 = new JButton("Etapa4 - Filtragem de cores");
		btnEtapa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IplImage img1, imghsv, imgbin;
				
				CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
				cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_WIDTH,640);
				cvSetCaptureProperty(capture1,CV_CAP_PROP_FRAME_HEIGHT,480);
				
				imghsv = cvCreateImage(cvSize(640,480),8,3);
				imgbin = cvCreateImage(cvSize(640,480),8,1);
				
				int i=1;
				
				while(i==1)
				{
						
					img1 = cvQueryFrame(capture1);
					
					if(img1 == null) break;
							
					cvCvtColor(img1,imghsv,CV_BGR2HSV);
					CvScalar minc = cvScalar(95,150,75,0), maxc = cvScalar(145,255,255,0);
					cvInRangeS(imghsv,minc,maxc,imgbin);
				
					cvShowImage("color",img1);
					cvShowImage("Binary",imgbin);
					
					char c = (char) cvWaitKey(15);
					if(c=='q'){
						cvDestroyWindow("color");
						cvDestroyWindow("Binary");
						return ;
					}
		
				}
				
				cvReleaseImage(imghsv);
				cvReleaseImage(imgbin);
				cvReleaseCapture(capture1);
			}
		});
		
		btnEtapa4.setBounds(10, 102, 312, 23);
		
		
		JButton btnEtapa5 = new JButton("Etapa 5 - Controle de Filtragem");
		btnEtapa5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IplImage img1,imghsv,imgbin;
				CvScalar minc = cvScalar(40,150,75,0), maxc = cvScalar(80,255,255,0);
				CvSeq contour1 = new CvSeq(), contour2;
				CvMemStorage storage = CvMemStorage.create();
				double areaMax = 1000, areaC=0;
				
				
				img1 = cvLoadImage("ColorImg.jpg");
				imghsv = cvCreateImage(cvGetSize(img1),8,3);
				imgbin = cvCreateImage(cvGetSize(img1),8,1);
				
				cvCvtColor(img1,imghsv,CV_BGR2HSV);
				cvInRangeS(imghsv,minc,maxc,imgbin);
				
				cvShowImage("Binary",imgbin);
				
				cvFindContours(imgbin,storage,contour1,Loader.sizeof(CvContour.class),
								CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
				
				contour2= contour1;
				
				while(contour1 != null && !contour1.isNull())
				{
					areaC = cvContourArea(contour1,CV_WHOLE_SEQ,1);
					
					if(areaC>areaMax)
						areaMax = areaC;
					
					contour1 = contour1.h_next();
					
				}
				
				while(contour2 !=null && !contour2.isNull())
				{
					areaC= cvContourArea(contour2,CV_WHOLE_SEQ,1);
					
					if(areaC<areaMax)
					{
						cvDrawContours(imgbin,contour2,CV_RGB(0,0,0),CV_RGB(0,0,0),
								0,CV_FILLED,8,cvPoint(0,0));
					}
					
					contour2=contour2.h_next();
				}
				
				cvShowImage("Color",img1);
				cvShowImage("CF",imgbin);
				cvWaitKey();
				
				
				cvReleaseImage(img1);
				cvReleaseImage(imghsv);
				cvReleaseImage(imgbin);
				cvReleaseMemStorage(storage);
			}
		});
		btnEtapa5.setBounds(10, 137, 312, 23);
		
		
		JButton btnEtapa6 = new JButton("Etapa6 - Rastreamento");
		btnEtapa6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IplImage img1,imghsv,imgbin;
				CvScalar Bminc = cvScalar(95,150,75,0), Bmaxc = cvScalar(145,255,255,0);
				CvScalar Rminc = cvScalar(150,150,75,0), Rmaxc = cvScalar(190,255,255,0);
				
				
				CvSeq contour1 = new CvSeq(), contour2;
				CvMemStorage storage = CvMemStorage.create();
				CvMoments moments = new CvMoments(Loader.sizeof(CvMoments.class));
						
				double areaMax, areaC=0;
				double m10,m01,m_area;
				
				int posX=0,posY=0;
				
						
				CvCapture capture1 = cvCreateCameraCapture(CV_CAP_ANY);
				imghsv = cvCreateImage(cvSize(640,480),8,3);
				imgbin = cvCreateImage(cvSize(640,480),8,1);
				
				int i=1;
				while(i==1)
				{
					
					img1 = cvQueryFrame(capture1);
								
					if(img1 ==null) 
						{
							System.err.println("No Image");
							break;
						}
				
					cvCvtColor(img1,imghsv,CV_BGR2HSV);
					cvInRangeS(imghsv,Bminc,Bmaxc,imgbin);
				
					contour1 = new CvSeq();
					areaMax= 1000;
				
					cvFindContours(imgbin,storage,contour1,Loader.sizeof(CvContour.class),
									CV_RETR_LIST,CV_LINK_RUNS,cvPoint(0,0));
				
					contour2= contour1;
				
					while(contour1 != null && !contour1.isNull())
					{
						areaC = cvContourArea(contour1,CV_WHOLE_SEQ,1);
					
						if(areaC>areaMax)
							areaMax = areaC;
					
						contour1 = contour1.h_next();
					
					}
				
					while(contour2 !=null && !contour2.isNull())
					{
						areaC= cvContourArea(contour2,CV_WHOLE_SEQ,1);
					
						if(areaC<areaMax)
						{
							cvDrawContours(imgbin,contour2,CV_RGB(0,0,0),CV_RGB(0,0,0),
									0,CV_FILLED,8,cvPoint(0,0));
						}
					
						contour2=contour2.h_next();
					}
				
					cvMoments(imgbin,moments,1);
					
					m10 = cvGetSpatialMoment(moments,1,0);
					m01 = cvGetSpatialMoment(moments,0,1);
					m_area = cvGetCentralMoment(moments,0,0);
					
					posX = (int) (m10/m_area);
					posY = (int) (m01/m_area);
					
					if(posX>0 && posY>0)
						System.out.println("x = "+posX+", y= "+posY);
					
					cvCircle(img1, cvPoint(posX,posY), 5, cvScalar(0,255,0,0), 9,0,0);
							
					cvShowImage("Color",img1);
					cvShowImage("CF",imgbin);
					char c = (char)cvWaitKey(15);
					if(c=='q'){
						cvDestroyWindow("Color");
						cvDestroyWindow("CF");
						return ;
					}

			
				}

				
				cvReleaseImage(imghsv);
				cvReleaseImage(imgbin);
				cvReleaseMemStorage(storage);
				cvReleaseCapture(capture1);
			}
		});
		btnEtapa6.setBounds(10, 171, 312, 23);
		
		JButton btnDocumentao = new JButton("Documenta\u00E7\u00E3o");
		btnDocumentao.setBounds(915, 68, 259, 23);
		
		JButton btnVisoComputacional = new JButton("Vis\u00E3o Computacional");
		btnVisoComputacional.setBounds(915, 0, 259, 23);
		
		JButton btnProcessamentoDigitalDe = new JButton("Processamento Digital de Imagens");
		btnProcessamentoDigitalDe.setBounds(915, 34, 259, 23);
		
		JButton btnOk = new JButton("Projeto Final - Mouse Virtual");
		btnOk.setBounds(10, 225, 312, 23);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(600, 527, 89, 23);
		
		
		btnDocumentao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Doc doc = new Doc();
				doc.setVisible(true);	
				dispose();
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
	
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		
		//
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IplImage img1,imgbinG, imgbinB;
				IplImage imghsv;			
				
				CvScalar Bminc = cvScalar(95,150,75,0), Bmaxc = cvScalar(145,255,255,0);
				CvScalar Gminc = cvScalar(40,50,60,0), Gmaxc = cvScalar(80,255,255,0);
				
				//img1 = cvLoadImage("Pic.jpg");
				CvArr mask;
				
				imghsv = cvCreateImage(cvSize(w,h),8,3);
				imgbinG = cvCreateImage(cvSize(w,h),8,1);
				imgbinB = cvCreateImage(cvSize(w,h),8,1);
				IplImage imgC = cvCreateImage(cvSize(w,h),8,1);
				CvSeq contour1 = new CvSeq(), contour2=null;
				CvMemStorage storage = CvMemStorage.create();
				CvMoments moments = new CvMoments(Loader.sizeof(CvMoments.class));
				
				//int i=1;
				while(true)
				{
						
					img1 = cvQueryFrame(capture1);
					if(img1 == null){
						System.err.println("No Image");
						break;
						}
						
					try {
						imgbinB = ccmFilter.Filter(img1,imghsv,imgbinB,Bmaxc, Bminc, contour1, contour2, storage,moments,1,0);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						imgbinG = ccmFilter.Filter(img1,imghsv,imgbinG,Gmaxc, Gminc, contour1, contour2, storage,moments,0,1);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					cvOr(imgbinB,imgbinG,imgC,mask=null);
					cvShowImage("Combined",imgC);	
					cvShowImage("Original",img1);
					char c = (char)cvWaitKey(15);
					if(c=='q') {
						cvDestroyWindow("Original");
						cvDestroyWindow("Combined");
						return ;
						}
							
				}
				cvReleaseImage(imghsv);
				cvReleaseImage(imgbinG);
				cvReleaseImage(imgbinB);
				cvReleaseImage(imghsv);
				cvReleaseMemStorage(storage);
				cvReleaseCapture(capture1);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnOk);
		contentPane.add(btnEtapa1);
		contentPane.add(btnEtapa2);
		contentPane.add(btnEtapa3);
		contentPane.add(btnEtapa4);
		contentPane.add(btnExit);
		contentPane.add(btnDocumentao);
		contentPane.add(btnVisoComputacional);
		contentPane.add(btnProcessamentoDigitalDe);
		contentPane.add(btnEtapa5);
		contentPane.add(btnEtapa6);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Bruna\\Documents\\Rafael\\1.jpg"));
		label.setBounds(10, 0, 1164, 550);
		contentPane.add(label);
	}
}
