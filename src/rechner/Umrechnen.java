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
 * Dies ist die einzige Klasse, welche sowohl die Grafik als auch die internen Berechnungen steuert.<br>
 * Es ist unterteilt in vier Textfelder, wobei man in jedes eine Zahl eingeben kann und die anderen sich vollautomatisch veraendern.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class Umrechnen {

	private static final DecimalFormat d = new DecimalFormat("#.00");

	private static final double[] multiplicator = {1,2,4, 20};

	private JFormattedTextField[] felder;

	
	private Umrechnen() {
		d.setGroupingUsed(false); 
		d.setMaximumIntegerDigits(20);
		NumberFormatter formatter = new NumberFormatter(d);
		formatter.setAllowsInvalid(false);

		felder = new JFormattedTextField[4];

		for (int i = 0; i < 4; i++) {
			felder[i] = new JFormattedTextField(formatter);
			addKeyListener(i);
		}
		
		Object[] zahlenfrage = {"<html><b>Bitte gib Dein Startgeld ein.</b></html>", "Euronen", felder[0], "Mark", felder[1], "Ostmark", felder[2], "Ostmark auf dem Schwarzmarkt", felder[3]};
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

		setzeAlleFelder(1, -1);

	    dialog.setVisible(true);
	    if(!dialog.isShowing()) {
	    	System.exit(0);
	    }
	}

	private void addKeyListener(int i) {
		felder[i].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				setzeAlleFelder(Double.parseDouble("0" + felder[i].getText().replace(',', '.')) / multiplicator[i], i);
			}
		});
	}

	private void setzeAlleFelder(double euroWert, int ignored) {
		for (int i = 0; i < 4; i++) {
			if(i != ignored) {
				felder[i].setText(d.format(euroWert * multiplicator[i]));
			}
		}
	}

	
	public static void main(String[] args) {
		new Umrechnen();
	}
}