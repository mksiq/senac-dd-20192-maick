package view.lista1;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.lista1.FuncionarioDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Funcionario;
import model.entity.lista1.Gerente;
import model.entity.lista1.Operacional;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroFuncionarioView {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSalarioBruto;
	private JTextField txtComissao;
	private JComboBox cbTipo;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JLabel lblComissao;
	private JLabel lblSalarioBruto;
	private Funcionario novoFuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionarioView window = new CadastroFuncionarioView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroFuncionarioView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 92, 26);
		frame.getContentPane().add(lblNome);
		
		lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(20, 70, 92, 26);
		frame.getContentPane().add(lblCpf);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(20, 120, 92, 26);
		frame.getContentPane().add(lblIdade);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20, 154, 92, 26);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 203, 92, 26);
		frame.getContentPane().add(lblTipo);
		
		
		lblComissao = new JLabel("Comissao:");
		lblComissao.setBounds(20, 303, 122, 26);
		frame.getContentPane().add(lblComissao);
		
		txtNome = new JTextField();
		txtNome.setBounds(180, 21, 186, 32);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setText("");
		txtCPF.setBounds(180, 70, 186, 32);
		frame.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
		ButtonGroup group = new ButtonGroup();
		rbMasculino = new JRadioButton("M");
		
		rbMasculino.setBounds(180, 154, 74, 35);
		frame.getContentPane().add(rbMasculino);
		
		rbFeminino = new JRadioButton("F");
		rbFeminino.setBounds(267, 154, 92, 35);
		frame.getContentPane().add(rbFeminino);
		
		group.add(rbFeminino);
		group.add(rbMasculino);
		
		
		
		//AQUI
		
		String [] tipos = {"Selecionar", FuncionarioDAO.TIPO_FUNCIONARIO_OPERACIONAL,
				FuncionarioDAO.TIPO_FUNCIONARIO_DIRETOR,
				FuncionarioDAO.TIPO_FUNCIONARIO_GERENTE
		};
		cbTipo = new JComboBox(tipos);
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// se selecionou de D ou G
				String tipoSelecionado = (String) cbTipo.getSelectedItem();
				if (tipoSelecionado.equals(FuncionarioDAO.TIPO_FUNCIONARIO_DIRETOR)){
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
					novoFuncionario = new Diretor();
					
				} else if (tipoSelecionado.equals(FuncionarioDAO.TIPO_FUNCIONARIO_GERENTE)) {
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
					novoFuncionario = new Gerente();
					
				} else if (tipoSelecionado.equals(FuncionarioDAO.TIPO_FUNCIONARIO_OPERACIONAL)) {
					txtComissao.setVisible(false);
					lblComissao.setVisible(false);
					txtComissao.setText("");
					novoFuncionario = new Operacional();
				} else {
					novoFuncionario = null;
				}
				
				// se nao esconde comissao
				
			}
		});
		cbTipo.setBounds(180, 200, 186, 32);
		frame.getContentPane().add(cbTipo);
		
		txtSalarioBruto = new JTextField();
		txtSalarioBruto.setText("");
		txtSalarioBruto.setBounds(180, 250, 186, 32);
		frame.getContentPane().add(txtSalarioBruto);
		txtSalarioBruto.setColumns(10);
		
		JSpinner spIdade = new JSpinner();
		spIdade.setBounds(180, 117, 186, 32);
		frame.getContentPane().add(spIdade);
		
		txtComissao = new JTextField();
		txtComissao.setText("");
		txtComissao.setBounds(180, 300, 186, 32);
		frame.getContentPane().add(txtComissao);
		txtComissao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(novoFuncionario == null) {
					JOptionPane.showMessageDialog(null, "Selecione um tipo", "Atenção!", JOptionPane.ERROR_MESSAGE);
				} else {
					novoFuncionario.setNome(txtNome.getText());
					novoFuncionario.setCpf(txtCPF.getText());
					novoFuncionario.setIdade(Integer.parseInt((String) spIdade.getValue()));
					
				}
				
				
			}
		});
		btnSalvar.setBounds(82, 382, 232, 88);
		frame.getContentPane().add(btnSalvar);
		
		lblSalarioBruto = new JLabel("Salario Bruto");
		lblSalarioBruto.setBounds(20, 250, 122, 26);
		frame.getContentPane().add(lblSalarioBruto);
	}
}
