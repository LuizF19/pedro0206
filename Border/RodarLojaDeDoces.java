import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RodarLojaDeDoces {

    public static void main(String[] args) {
        // Define o Look and Feel para uma aparência mais próxima do sistema operacional (Opcional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Garante que a criação da GUI ocorra na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Cria uma instância da nossa janela da loja e a torna visível
                new LojadeDoces().setVisible(true);
            }
        });
    }
}