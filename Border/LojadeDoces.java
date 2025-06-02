import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
// A linha "import java.text.DecimalFormat;" foi removida

public class LojadeDoces extends JFrame {

    private JTextField quantidadeBrigadeiroField;
    private JTextField quantidadeCookieField;
    private JTextField quantidadePudimField;

    private final double PRECO_BRIGADEIRO = 10.00;
    private final double PRECO_COOKIE = 15.00;
    private final double PRECO_PUDIM = 25.00;

    private JLabel labelImagemBrigadeiro;
    private JLabel labelImagemCookie;
    private JLabel labelImagemPudim;

    public LojadeDoces() {
        super("Venda de doces");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setLayout(new BorderLayout(10, 10));

        JLabel tituloLojaLabel = new JLabel("Loja de doces", SwingConstants.CENTER);
        tituloLojaLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLojaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(tituloLojaLabel, BorderLayout.NORTH);

        JPanel painelProdutosPrincipal = new JPanel();
        painelProdutosPrincipal.setLayout(new BoxLayout(painelProdutosPrincipal, BoxLayout.Y_AXIS));
        painelProdutosPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        labelImagemBrigadeiro = criarLabelDeImagem("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Border\\brigadeiro.png", 100, 100);
        quantidadeBrigadeiroField = new JTextField("1", 3);
        // Chamada ao método formatPrice que foi simplificado
        JPanel painelBrigadeiro = criarPainelProduto(labelImagemBrigadeiro, "R$ " + formatPrice(PRECO_BRIGADEIRO), quantidadeBrigadeiroField);
        painelProdutosPrincipal.add(painelBrigadeiro);
        painelProdutosPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        labelImagemCookie = criarLabelDeImagem("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Border\\coookie.png", 100, 100);
        quantidadeCookieField = new JTextField("1", 3);
        JPanel painelCookie = criarPainelProduto(labelImagemCookie, "R$ " + formatPrice(PRECO_COOKIE), quantidadeCookieField);
        painelProdutosPrincipal.add(painelCookie);
        painelProdutosPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        labelImagemPudim = criarLabelDeImagem("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Border\\puudim.png", 100, 100);
        quantidadePudimField = new JTextField("1", 3);
        JPanel painelPudim = criarPainelProduto(labelImagemPudim, "R$ " + formatPrice(PRECO_PUDIM), quantidadePudimField);
        painelProdutosPrincipal.add(painelPudim);

        add(painelProdutosPrincipal, BorderLayout.CENTER);

        JButton pedirButton = new JButton("Pedir");
        pedirButton.setFont(new Font("Arial", Font.BOLD, 18));
        pedirButton.setPreferredSize(new Dimension(100, 40));

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        painelBotao.add(pedirButton);
        add(painelBotao, BorderLayout.SOUTH);

        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int qtdBrigadeiro = Integer.parseInt(quantidadeBrigadeiroField.getText());
                    int qtdCookie = Integer.parseInt(quantidadeCookieField.getText());
                    int qtdPudim = Integer.parseInt(quantidadePudimField.getText());

                    if (qtdBrigadeiro < 0 || qtdCookie < 0 || qtdPudim < 0) {
                        JOptionPane.showMessageDialog(LojadeDoces.this,
                                "A quantidade não pode ser negativa.",
                                "Erro na Quantidade",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double total = (qtdBrigadeiro * PRECO_BRIGADEIRO) +
                                   (qtdCookie * PRECO_COOKIE) +
                                   (qtdPudim * PRECO_PUDIM);

                    // DecimalFormat foi removido. O total será convertido para String diretamente.
                    // String totalFormatado = String.valueOf(total); // Converte o double para String

                    JOptionPane.showMessageDialog(LojadeDoces.this,
                            "O total da compra: " + total, // Exibe o total diretamente
                            "Sistema de doces",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LojadeDoces.this,
                            "Por favor, insira um número válido na quantidade.",
                            "Erro de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(null);
    }

    private JPanel criarPainelProduto(JLabel labelImagem, String textoPreco, JTextField campoQuantidade) {
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(labelImagem, gbc);

        JLabel labelPreco = new JLabel(textoPreco);
        labelPreco.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(labelPreco, gbc);

        campoQuantidade.setFont(new Font("Arial", Font.PLAIN, 16));
        campoQuantidade.setPreferredSize(new Dimension(40, 25));
        gbc.gridx = 2;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(campoQuantidade, gbc);
        
        return painel;
    }

    private JLabel criarLabelDeImagem(String caminhoImagem, int largura, int altura) {
        ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
        JLabel labelImagem = new JLabel();
        if (iconeOriginal.getImageLoadStatus() == MediaTracker.ERRORED) {
            labelImagem.setText("[Imagem não encontrada: " + caminhoImagem + "]");
            labelImagem.setPreferredSize(new Dimension(largura, altura));
            labelImagem.setOpaque(true);
            labelImagem.setBackground(Color.LIGHT_GRAY);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            Image imagem = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(imagem));
        }
        return labelImagem;
    }
    
    // Método auxiliar para formatar o preço, agora simplificado
    private String formatPrice(double price) {
        // Simplesmente converte o double para String.
        // Para exibir "10.0" em vez de "10.00", ou "15.0" em vez de "15.00"
        return String.valueOf(price);
    }
}