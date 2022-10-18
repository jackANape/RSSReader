import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JEditorPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private static JEditorPane editorPane;
	private static JScrollPane scrollPane;

	
	public static void printToText() throws BadLocationException, IOException
	{
		RSSFeedParser parser = new RSSFeedParser(
                "https://www.news.com.au/content-feeds/latest-news-national/");
        Feed feed = parser.readFeed();
        String htmlComent = "";
        
        for (FeedMessage message : feed.getMessages()) {
        	
        	htmlComent += 
        			  "<font size = '+2'><b align ='left'>" + message.title + "</b></font><br>"
        			+ "<i align = 'left'>" + message.pubDate + "</i><br>"
        			+  "<font size = '+1'>" + message.description + "</b></font><br>"
        			+ "<p style = 'color:blue'><a href = " + message.link +">" + "read more.." + "</a></p><br><br>";
    
            
        }
        
        
        append(htmlComent + "\n");
        editorPane.setCaretPosition(0);
        
    

	}
	
	public static void append(String s) throws BadLocationException, IOException {
	
	      HTMLEditorKit kit = new HTMLEditorKit();
	      HTMLDocument doc2 = new HTMLDocument();
	      editorPane.setEditorKit(kit);
	      editorPane.setDocument(doc2);
	      HTMLDocument doc = (HTMLDocument)editorPane.getDocument();
	      kit.insertHTML(doc, doc2.getLength(), s, 0, 0, null);
	      //kit.insertHTML(doc, doc2.getLength(),, s, 0, 0, null);
	   
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("9News RSS Feed");
					frame.setVisible(true);
					printToText();
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 555);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				try {
					printToText();
				} catch (BadLocationException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

    
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(btnNewButton)
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		editorPane = new JEditorPane("text/html", "");		


		
		
		editorPane.setBackground(Color.WHITE);
		editorPane.addHyperlinkListener(new HyperlinkListener() {
		    public void hyperlinkUpdate(HyperlinkEvent e) {
		        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		        	if(Desktop.isDesktopSupported()) {
		        	    try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
		        }
		    }
		});
		
		
		scrollPane.setViewportView(editorPane);
		editorPane.setEditable(false);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
}
