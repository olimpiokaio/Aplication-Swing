package swing1;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	**/
	JTextArea textArea;
	
	/* ---------------------------------------------------------------------------- */
	
	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// adicionando componentes ao painel
		add(textArea);
	}
	
	public void adicionarTexto(String txt) {
		textArea.append(txt);
	}
	
}
