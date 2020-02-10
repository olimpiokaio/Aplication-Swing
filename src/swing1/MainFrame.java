package swing1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************/
	/* --- componentes, objetos e interfaces --- */
	/*******************************************************************************************/
	private final TextPanel painel;
	private final Tolbar tolbar;
	private final FormPanel formPanel;
	
	/*******************************************************************************************/
	
	public MainFrame() {
		super("ECENTRICA SISTEMAS");
		setLayout(new BorderLayout());
		
		// adiciona painel
		painel = new TextPanel();
		
		// adiciona tolbar
		tolbar = new Tolbar();
		
		// adiciona formulario
		formPanel = new FormPanel();
		
		// adiciona jmenu bar
		setJMenuBar(createMenuBar());
		
		
		tolbar.setStringListener(new StringListener() {
			@Override
			public void emitidoTexto(String texto) {
				painel.adicionarTexto(texto);
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			// set um EventListener no atributo formListener do FormPanel, que vai ser refletido o seus valores no MainFrame pelo FormListener(EventListener)
			public void formEventoOcorrido(FormEvent e) {
				String nome = e.getName();
				String occupation = e.getOccupation();
				int ageCategory = e.getAgeCategory();
				String empCat = e.getEmpCat();
				String gen = e.getGen();
				
				System.out.println(gen);
				String txt = String.format("%s : %s : %d : %s%n",
						nome, occupation, ageCategory, empCat);
				
				painel.adicionarTexto(txt);
			}
			
		});
		
/*******************************************************************************************************/
/*******************************************************************************************************/		
/*******************************************************************************************************/
		
		// adicionado components
		add(tolbar, BorderLayout.NORTH); // adiciona o butão ao norte(cabeçalho) do MainFrame
		add(new JScrollPane(painel), BorderLayout.CENTER); // adiciona ao centro do MainFrame
		add(formPanel, BorderLayout.WEST);
		
		// configurações do MainFrame
		this.setMinimumSize(new Dimension(600, 500));
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		// MENU FILE
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDate = new JMenuItem("Export Data");
		JMenuItem importData = new JMenuItem("Import Data");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDate);
		fileMenu.add(importData);
		fileMenu.addSeparator(); // linha horizontal(divizoria de itens)
		fileMenu.add(exitItem);
		
		// MENU WINDOW
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show...");
		final JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"Do your really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if (action == JOptionPane.OK_OPTION) {
					System.exit(EXIT_ON_CLOSE);					
				}
			}
		});
		
		// set up mnemonic (adiciona atalho para selecionar items do menu)
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		// set up Accelerator (atalho para execultar items do menu sem precisar fazer o caminho completo para acessalo)
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		return menuBar;
	}
	
}