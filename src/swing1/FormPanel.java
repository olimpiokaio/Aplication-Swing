package swing1;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 */
	private FormListener formListener;
	private final JLabel nameLabel;
	private final JLabel occupationLabel;
	private final JTextField nameField;
	private final JTextField occupationField;
	private final JButton btOk;
	private final JList ageList;
	private final JComboBox empCombo;
	private final JCheckBox citizenCheck;
	private final JTextField taxField;
	private final JLabel taxLabel;
	
	private final JRadioButton maleRadio;
	private final JRadioButton femaRadio;
	private final ButtonGroup groupButton;
	
	
	public FormPanel() {
		// pega o tamanho default do componente vazio
		Dimension dim = getPreferredSize();
		// System.out.println(dim);
		dim.width = 300;
		// redefine a largura default do componente vazio
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(13);
		occupationField = new JTextField(13);
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(13);
		taxLabel = new JLabel("Tax ID: ");
		btOk = new JButton("OK");
		
		maleRadio = new JRadioButton("male");
		femaRadio = new JRadioButton("female");
		maleRadio.setActionCommand("male");
		femaRadio.setActionCommand("female");
		groupButton = new ButtonGroup();
		maleRadio.setSelected(true);
		
		groupButton.add(maleRadio);
		groupButton.add(femaRadio);
		
		
		// set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		citizenCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected(); // retorna se o citizenCheck foi selecionado(true ou false)
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});
		
		
		// set up list box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel);
		ageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // não deixa escolher mais de um item da lista
		ageList.setPreferredSize(new Dimension(146, 68));
		ageList.setBorder(BorderFactory.createEtchedBorder()); // criar borda
		ageList.setSelectedIndex(1); // define qual indice da lista já vem selecionado
		
		// set up combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		
		btOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				String taxId = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				String gen = groupButton.getSelection().getActionCommand();
				
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), 
						empCat, taxId, usCitizen, gen);
				
				if (formListener != null) {
					formListener.formEventoOcorrido(ev);
					// limpa formulario
					nameField.setText(null);
					occupationField.setText(null);
				}
			}
		});
		
		// set up mnemonic (adiciona atalho para selecionar items do menu)
		btOk.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		
		// cria obj da classe borda com titulo
		Border innerBorder = BorderFactory.createTitledBorder("add Person");
		// cria obj da classe borda com espaços vazios
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		// adiciona duas bordar no componente, 1° borda fronteira, 2° borda interna
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	
		layoutComponents();
		
	} // fim do construtor do FormPanel
	
	public void layoutComponents() {
		// define o layout do FormPanel
		setLayout(new GridBagLayout());
		// define o comportamento do gridBagLayout
		GridBagConstraints gc = new GridBagConstraints();
		
		// diz aos seus componentes, se deve ocupar todos os espaço na célula, ou não. Também pode ocupar somente na
		// horizontal, vertical ou em ambas as direções
		// (CONFIGURAÇÃO NECESSARIA PARA FUNCINAMENTO DO GRIDBAGLAYOUT!!)
		gc.fill = GridBagConstraints.NONE; 
		
		
		/*----------------------------------- PRIMEIRA LINHA  ------------------------------------ */
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 0; // posicao da celula da vertical
		gc.gridy = 0; // posicao da celula na horizontal
		gc.anchor = GridBagConstraints.LINE_END; // especifica o alinhamento do componente dentro da celula que o controle adere
		gc.insets = new Insets(0, 0, 0, 5); // define a margem em pixeis do componente;
		add(nameLabel, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5); // define a margem em pixeis do componente;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(occupationField, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(ageList, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employed: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(empCombo, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("USA Citizen: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(citizenCheck, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.001; // distancia de uma celula para a outra na vertica
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(taxField, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na horizontal
		gc.weighty = 0.0002; // distancia de uma celula para a outra na vertica
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add( new JLabel("Gen: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(maleRadio, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na  horizontal
		gc.weighty = 0.005; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(femaRadio, gc);
		
		/*----------------------------------- PRÓXIMA LINHA  ------------------------------------ */
		gc.gridy++;
		
		gc.weightx = 1; // distancia de uma celula para a outra na  horizontal
		gc.weighty = 1; // distancia de uma celula para a outra na vertical
		
		gc.gridx = 1;
		gc.weighty = 0.01;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // posiciona na primeira linha(no topo) do espaço dentro celula
		add(btOk, gc);
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
}

class AgeCategory {
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
	
}







