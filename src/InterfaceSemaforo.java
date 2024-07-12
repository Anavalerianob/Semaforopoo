import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceSemaforo extends JFrame {
    private Cruzamento cruzamento;
    private Semaforo semaforo;
    private JPanel painelDesenho;
    private Thread semaforoThread;

    public InterfaceSemaforo() {
        this.cruzamento = new Cruzamento();
        this.setTitle("Gerenciador de Semáforos");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.painelDesenho = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = 150;
                int y = 50;
                for (Semaforo semaforo : cruzamento.getSemaforos()) {
                    semaforo.desenha(g, x, y);
                    x += 200;
                }
                if (semaforo != null) {
                    semaforo.desenha(g, 50, 300);
                }
            }
        };

        this.add(painelDesenho, BorderLayout.CENTER);

        JPanel painelControles = new JPanel();

        JButton botaoCriarCruzamento = new JButton("Criar Cruzamento");
        botaoCriarCruzamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cruzamento = new Cruzamento();
                Semaforo semaforo1 = new Semaforo("tradicional", 7, 7);
                Semaforo semaforo2 = new Semaforo("tradicional", 7, 7);
                cruzamento.adicionaSemaforo(semaforo1);
                cruzamento.adicionaSemaforo(semaforo2);
                cruzamento.sincroniza();
                painelDesenho.repaint();
            }
        });

        JButton botaoExcluirCruzamento = new JButton("Excluir Cruzamento");
        botaoExcluirCruzamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cruzamento.removeSemaforos();
                painelDesenho.repaint();
            }
        });

        JButton botaoCriarSemaforo = new JButton("Criar Semáforo");
        botaoCriarSemaforo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                semaforo = new Semaforo("tradicional", 7, 7);
                if (semaforoThread != null && semaforoThread.isAlive()) {
                    semaforoThread.interrupt();
                }
                semaforoThread = new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (semaforo != null) {
                            if (semaforo.estaAberto()) {
                                for (int i = 0; i < semaforo.getTempoAberto(); i++) {
                                    semaforo.atualizaContador();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e1) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                                semaforo.setAtencao();
                                for (int i = 0; i < 2; i++) {
                                    semaforo.atualizaContador();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e1) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                                semaforo.fecha();
                            } else if (semaforo.estaFechado()) {
                                for (int i = 0; i < semaforo.getTempoFechado(); i++) {
                                    semaforo.atualizaContador();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e1) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                                semaforo.abre();
                            }
                        }
                    }
                });
                semaforoThread.start();
                painelDesenho.repaint();
            }
        });

        JButton botaoExcluirSemaforo = new JButton("Excluir Semáforo");
        botaoExcluirSemaforo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaforoThread != null && semaforoThread.isAlive()) {
                    semaforoThread.interrupt();
                }
                semaforo = null;
                painelDesenho.repaint();
            }
        });

        JButton botaoAbrirSemaforo = new JButton("Abrir Semáforo");
        botaoAbrirSemaforo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaforo != null) {
                    semaforo.abre();
                    painelDesenho.repaint();
                }
            }
        });

        JButton botaoFecharSemaforo = new JButton("Fechar Semáforo");
        botaoFecharSemaforo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaforo != null) {
                    semaforo.fecha();
                    painelDesenho.repaint();
                }
            }
        });

        JButton botaoMudarTipo = new JButton("Mudar Tipo Semáforo");
        botaoMudarTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Semaforo semaforo : cruzamento.getSemaforos()) {
                    if (semaforo.getTipo().equals("digital")) {
                        semaforo.mudaTipo("tradicional");
                    } else {
                        semaforo.mudaTipo("digital");
                    }
                }
                if (semaforo != null) {
                    if (semaforo.getTipo().equals("digital")) {
                        semaforo.mudaTipo("tradicional");
                    } else {
                        semaforo.mudaTipo("digital");
                    }
                }
                painelDesenho.repaint();
            }
        });

        painelControles.add(botaoCriarCruzamento);
        painelControles.add(botaoExcluirCruzamento);
        painelControles.add(botaoCriarSemaforo);
        painelControles.add(botaoExcluirSemaforo);
        painelControles.add(botaoAbrirSemaforo);
        painelControles.add(botaoFecharSemaforo);
        painelControles.add(botaoMudarTipo);

        this.add(painelControles, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> painelDesenho.repaint());
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceSemaforo app = new InterfaceSemaforo();
            app.setVisible(true);
        });
    }
}
    