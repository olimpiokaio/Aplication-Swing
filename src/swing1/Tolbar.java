package swing1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;;

public class Tolbar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;	
	/*******************************************************************************************/
	/* --- componentes --- */
	/*******************************************************************************************/
	private final JButton btnHello;
	private final JButton btnGoodbay;
	private StringListener textListener;
	
	/*-----------------------------------------------------------------------------------------*/
	public Tolbar() {
		// adiciona uma borda ao componente
		setBorder(BorderFactory.createEtchedBorder());
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// cria btnHello
		btnHello = new JButton("Hello");
		btnHello.addActionListener(this);
		
		// cria btnBay
		btnGoodbay = new JButton("BGoodbay");
		btnGoodbay.addActionListener(this);
		
		// adiciona os componentes no Tolbar
		add(btnHello);
		add(btnGoodbay);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnHello)) {
			
			if (textListener != null) {
				textListener.emitidoTexto("Hello!\n");
			} else {
				System.out.println("StringListener da classe Tolbar e nulo");
			}
			// this.textPanel.adicionarTexto("Hello!\n");
		} 
		else if(e.getSource().equals(btnGoodbay)) {
			
			if (textListener != null) {
				textListener.emitidoTexto("Goodbay!\n");
			} else {
				System.out.println("StringListener da classe Tolbar e nulo");
			}
			// this.textPanel.adicionarTexto("Goodbay!\n");
		}
		
	}
	
}










