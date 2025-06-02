import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LojadeDocesGridLayout extends JFrame {

    private JTextField quantidadeBrigadeiroField;
    private JTextField quantidadeCookieField;
    private JTextField quantidadePudimField;

    private final double PRECO_BRIGADEIRO = 10.00;
    private final double PRECO_COOKIE = 15.00;
    private final double PRECO_PUDIM = 25.00;

    public LojadeDocesGridLayout() {
        super("Venda de doces - GridLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600); // Ajuste o tamanho conforme necessário
        setLayout(new BorderLayout(10, 10));

        // --- TÍTULO ---
        JLabel tituloLojaLabel = new JLabel("Loja de doces", SwingConstants.CENTER);
        tituloLojaLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLojaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(tituloLojaLabel, BorderLayout.NORTH);

        // --- PAINEL CENTRAL PARA OS PRODUTOS (USANDO GRIDLAYOUT) ---
        JPanel painelCentralProdutos = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 linhas, 1 coluna
        painelCentralProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Produto 1: Brigadeiro ---
        quantidadeBrigadeiroField = new JTextField("1", 5);
        JPanel painelBrigadeiro = criarPainelProdutoGridLayout("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Grid\\brigadeiro.png",
                "R$ " + formatPrice(PRECO_BRIGADEIRO),
                quantidadeBrigadeiroField);
        painelCentralProdutos.add(painelBrigadeiro);

        // --- Produto 2: Cookie ---
        quantidadeCookieField = new JTextField("1", 5);
        JPanel painelCookie = criarPainelProdutoGridLayout("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Grid\\coookie.png", // CORRIGIDO
                "R$ " + formatPrice(PRECO_COOKIE),
                quantidadeCookieField);
        painelCentralProdutos.add(painelCookie);

        // --- Produto 3: Pudim ---
        quantidadePudimField = new JTextField("2", 5);
        JPanel painelPudim = criarPainelProdutoGridLayout("C:\\Users\\Aluno\\Documents\\Codigos pedor\\0206\\Grid\\puudim.png", // CORRIGIDO
                "R$ " + formatPrice(PRECO_PUDIM),
                quantidadePudimField);
        painelCentralProdutos.add(painelPudim);

        add(painelCentralProdutos, BorderLayout.CENTER);

        // --- BOTÃO PEDIR ---
        JButton pedirButton = new JButton("Pedir");
        pedirButton.setFont(new Font("Arial", Font.BOLD, 18));
        pedirButton.setPreferredSize(new Dimension(120, 45)); // Tamanho do botão

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        painelBotao.add(pedirButton);
        add(painelBotao, BorderLayout.SOUTH);

        // --- AÇÃO DO BOTÃO PEDIR ---
        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int qtdBrigadeiro = Integer.parseInt(quantidadeBrigadeiroField.getText());
                    int qtdCookie = Integer.parseInt(quantidadeCookieField.getText());
                    int qtdPudim = Integer.parseInt(quantidadePudimField.getText());

                    if (qtdBrigadeiro < 0 || qtdCookie < 0 || qtdPudim < 0) {
                        JOptionPane.showMessageDialog(LojadeDocesGridLayout.this,
                                "A quantidade não pode ser negativa.",
                                "Erro na Quantidade",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double total = (qtdBrigadeiro * PRECO_BRIGADEIRO) +
                                   (qtdCookie * PRECO_COOKIE) +
                                   (qtdPudim * PRECO_PUDIM);

                    JOptionPane.showMessageDialog(LojadeDocesGridLayout.this,
                            "O total da compra: " + total,
                            "Sistema de doces",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(LojadeDocesGridLayout.this,
                            "Por favor, insira um número válido na quantidade.",
                            "Erro de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(null); // Centraliza a janela
    }

    // Método auxiliar para criar o painel de cada produto usando GridLayout(1,3)
    private JPanel criarPainelProdutoGridLayout(String caminhoImagem, String textoPreco, JTextField campoQuantidade) {
        JPanel painelProduto = new JPanel(new GridLayout(1, 3, 5, 5)); // 1 linha, 3 colunas, com espaçamento

        // 1. Imagem
        JLabel labelImagem = criarLabelDeImagem(caminhoImagem, 100, 100); // Tamanho desejado da imagem
        // Para centralizar a imagem dentro da célula do GridLayout, podemos colocá-la em outro painel
        JPanel painelImagem = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelImagem.add(labelImagem);
        painelProduto.add(painelImagem);

        // 2. Preço
        JLabel labelPreco = new JLabel(textoPreco, SwingConstants.CENTER); // Centraliza o texto
        labelPreco.setFont(new Font("Arial", Font.PLAIN, 16));
        painelProduto.add(labelPreco);

        // 3. Quantidade
        // Para controlar melhor o tamanho do JTextField e centralizá-lo
        JPanel painelQuantidade = new JPanel(new FlowLayout(FlowLayout.CENTER));
        campoQuantidade.setFont(new Font("Arial", Font.PLAIN, 16));
        // O construtor do JTextField já define o número de colunas preferido (ex: 5)
        painelQuantidade.add(campoQuantidade);
        painelProduto.add(painelQuantidade);
        
        return painelProduto;
    }

    // Método auxiliar para criar e escalar a imagem em um JLabel (reutilizado)
    private JLabel criarLabelDeImagem(String caminhoImagem, int largura, int altura) {
        ImageIcon iconeOriginal = new ImageIcon(caminhoImagem);
        JLabel labelImagem = new JLabel();
        if (iconeOriginal.getImageLoadStatus() == MediaTracker.ERRORED) {
            labelImagem.setText("[Imagem: " + caminhoImagem.substring(caminhoImagem.lastIndexOf('/')+1) + " N/D]");
            labelImagem.setPreferredSize(new Dimension(largura, altura));
            labelImagem.setOpaque(true);
            labelImagem.setBackground(Color.LIGHT_GRAY);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            System.err.println("ERRO: Não foi possível carregar a imagem: " + caminhoImagem);
        } else {
            Image imagem = iconeOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(imagem));
        }
        return labelImagem;
    }
    
    // Método auxiliar para formatar o preço (reutilizado e simplificado)
    private String formatPrice(double price) {
        return String.valueOf(price);
    }
}