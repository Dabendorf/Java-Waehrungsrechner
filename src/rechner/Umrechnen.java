package rechner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Dies ist der Waehrungsrechner wie ihn das Kaenguru im Kaenguru-Manifest verwendet.<br>
 * Dies ist die einzige Klasse welche sowohl die Grafik als auch die internen Berechnungen steuert.<br>
 * Es ist unterteilt in vier Textfelder, wobei man in jedes eine Zahl eingeben kann und die anderen sich vollautomatisch veraendern.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class Umrechnen {
	
	private DecimalFormat d = new DecimalFormat("#.00");
	
	public Umrechnen() {
		d.setGroupingUsed(false); 
		d.setMaximumIntegerDigits(20);
		NumberFormatter formatter1 = new NumberFormatter(d);
		NumberFormatter formatter2 = new NumberFormatter(d);
		NumberFormatter formatter3 = new NumberFormatter(d);
		NumberFormatter formatter4 = new NumberFormatter(d);
		formatter1.setAllowsInvalid(false);
		formatter2.setAllowsInvalid(false);
		formatter3.setAllowsInvalid(false);
		formatter4.setAllowsInvalid(false);
		
		final JFormattedTextField eurofeld = new JFormattedTextField(formatter1);
		final JFormattedTextField markfeld = new JFormattedTextField(formatter2);
		final JFormattedTextField ostmarkfeld = new JFormattedTextField(formatter3);
		final JFormattedTextField schwarzmarktfeld = new JFormattedTextField(formatter4);
		
		eurofeld.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent ke) {
	        	String eurostring = eurofeld.getText();
	        	String[] eurostringarr = eurostring.split(",");
	        	double[] eurodoublearr = new double[2];
	        	eurodoublearr[0] = Double.parseDouble(eurostringarr[0]);
	        	eurodoublearr[1] = Double.parseDouble(eurostringarr[1]);
	        	double euro = eurodoublearr[0] + eurodoublearr[1]*0.01;
	        	
	        	markfeld.setText(d.format(2*euro));
	        	ostmarkfeld.setText(d.format(4*euro));
	        	schwarzmarktfeld.setText(d.format(20*euro));
	        }
	    });
		markfeld.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
	        	String markstring = markfeld.getText();
	        	String[] markstringarr = markstring.split(",");
	        	double[] markdoublearr = new double[2];
	        	markdoublearr[0] = Double.parseDouble(markstringarr[0]);
	        	markdoublearr[1] = Double.parseDouble(markstringarr[1]);
	        	double mark = markdoublearr[0] + markdoublearr[1]*0.01;
	        	
	        	eurofeld.setText(d.format(0.5*mark));
	        	ostmarkfeld.setText(d.format(2*mark));
	        	schwarzmarktfeld.setText(d.format(10*mark));
	        }
	    });
		ostmarkfeld.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
	        	String ostmarkstring = ostmarkfeld.getText();
	        	String[] ostmarkstringarr = ostmarkstring.split(",");
	        	double[] ostmarkdoublearr = new double[2];
	        	ostmarkdoublearr[0] = Double.parseDouble(ostmarkstringarr[0]);
	        	ostmarkdoublearr[1] = Double.parseDouble(ostmarkstringarr[1]);
	        	double ostmark = ostmarkdoublearr[0] + ostmarkdoublearr[1]*0.01;
	        	
	        	eurofeld.setText(d.format(0.25*ostmark));
	        	markfeld.setText(d.format(0.5*ostmark));
	        	schwarzmarktfeld.setText(d.format(5*ostmark));
	        }
	    });
		schwarzmarktfeld.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
	        	String schwarzmarktstring = schwarzmarktfeld.getText();
	        	String[] schwarzmarktstringarr = schwarzmarktstring.split(",");
	        	double[] schwarzmarktdoublearr = new double[2];
	        	schwarzmarktdoublearr[0] = Double.parseDouble(schwarzmarktstringarr[0]);
	        	schwarzmarktdoublearr[1] = Double.parseDouble(schwarzmarktstringarr[1]);
	        	double schwarzmarkt = schwarzmarktdoublearr[0] + schwarzmarktdoublearr[1]*0.01;
	        	
	        	eurofeld.setText(d.format(0.05*schwarzmarkt));
	        	markfeld.setText(d.format(0.1*schwarzmarkt));
	        	ostmarkfeld.setText(d.format(0.2*schwarzmarkt));
	        }
	    });
		
		Object[] zahlenfrage = {"<html><b>Bitte gib Dein Startgeld ein.</b></html>", "Euronen", eurofeld, "Mark", markfeld, "Ostmark", ostmarkfeld, "Ostmark auf dem Schwarzmarkt", schwarzmarktfeld};
		JOptionPane pane = new JOptionPane(zahlenfrage, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		JDialog dialog = pane.createDialog(null, "Währungsrechner");
		dialog.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	            System.exit(0);
	        }
	        public void windowClosed(WindowEvent we) {
	            System.exit(0);
	        }
	    });
		eurofeld.setText("1,00");
		markfeld.setText("2,00");
		ostmarkfeld.setText("4,00");
		schwarzmarktfeld.setText("20,00");
	    dialog.setVisible(true);
	    if(dialog.isShowing() == false) {
	    	System.exit(0);
	    }
	}
	
	public static void main(String[] args) {
		new Umrechnen();
	}

}