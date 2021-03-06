package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import classesBase.*;
import dados.Repositorio;
import dados.RepositorioArrayPessoa;
import excecoes.ElementoNaoEncontradoException;
import excecoes.EntradaInvalidaException;
import excecoes.RepositorioException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import principal.PaginaPrincipal;

@SuppressWarnings("serial")
public class RemoverAdmFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverAdmFrame frame = new RemoverAdmFrame();
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
	public RemoverAdmFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 114, 462, 148);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		JLabel lblSelecioneAOpcao = new JLabel("Selecione a opcao desejada:");
		lblSelecioneAOpcao.setBounds(40, 271, 268, 16);
		contentPane.add(lblSelecioneAOpcao);


		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que quer remover?");
				switch(opc){
				case 0: //sim
					try {
						Pessoa p= (Pessoa) comboBox.getSelectedItem();
						try {
							PaginaPrincipal.fachada.removerPessoa(p.getCpf());
						} catch (RepositorioException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EntradaInvalidaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(RemoverAdmFrame.this,"Administrador removido com sucesso.");
					} catch (ElementoNaoEncontradoException e1) {
					}
					voltar();
				
					break;
				case 1: //nao
					
					break;
				case 2: //cancelar
					break;
				}
			}
		});

		btnRemover.setBounds(40, 299, 146, 50);

		comboBox = new JComboBox();
		comboBox.removeAllItems();
		Iterator<Pessoa> it = PaginaPrincipal.fachada.getAdministradores().getIterator();
		while(it.hasNext()){
			comboBox.addItem(it.next());
		}
		if (comboBox.getSelectedItem() != null) {
			textArea.setText(((Pessoa) comboBox.getSelectedItem())
					.resumo());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					textArea.setText(((Pessoa) comboBox.getSelectedItem())
							.resumo());
				}
			}
		});

		comboBox.setBounds(40, 76, 462, 27);
		contentPane.add(comboBox);
		
						
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				setVisible(false);

			}
		});
		btnVoltar.setBounds(463, 360, 117, 50);
		contentPane.add(btnVoltar);

		JLabel lblSelecioneOAdministrador = new JLabel(
				"Selecione o administrador a ser editado:");
		lblSelecioneOAdministrador.setBounds(40, 30, 254, 16);
		contentPane.add(lblSelecioneOAdministrador);

		textField = new JTextField();
		textField.setBounds(40, 50, 321, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String procura = textField.getText();
				RepositorioArrayPessoa resultadoPesquisa = new RepositorioArrayPessoa();
				try {
					resultadoPesquisa = PaginaPrincipal.fachada.getAdministradores().procurarNome(procura);
				} catch (ElementoNaoEncontradoException e1) {
					String aviso = "A pesquisa n�o retornou resultados";
					JOptionPane.showMessageDialog(null, aviso);
				}
				comboBox.removeAllItems();
				Iterator<Pessoa> it = resultadoPesquisa.iterator();
				while (it.hasNext()) {
					Pessoa pessoa = it.next();
					comboBox.addItem(pessoa);
				}
			}
		});
		btnPesquisar.setBounds(377, 50, 125, 23);
		contentPane.add(btnPesquisar);

	}
	protected void voltar() {
		MenuPrincipal frame = new MenuPrincipal();
		frame.setVisible(true);
		setVisible(false);
	}
}
