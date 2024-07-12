import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Semaforo {
    private boolean aberto;
    private boolean fechado;
    private boolean atencao;
    private String tipo; // "digital" ou "tradicional"
    private int tempoAberto;
    private int tempoFechado;
    private int contadorTempo; // Contador para o cronômetro

    public Semaforo(String tipo, int tempoAberto, int tempoFechado) {
        this.tipo = tipo;
        this.tempoAberto = tempoAberto;
        this.tempoFechado = tempoFechado;
        this.fechado = true; // Inicialmente fechado
        this.contadorTempo = tempoFechado; // Inicializa o contador
    }

    public void abre() {
        this.aberto = true;
        this.fechado = false;
        this.atencao = false;
        this.contadorTempo = tempoAberto;
    }

    public void fecha() {
        this.aberto = false;
        this.fechado = true;
        this.atencao = false;
        this.contadorTempo = tempoFechado;
    }

    public void setAtencao() {
        this.aberto = false;
        this.fechado = false;
        this.atencao = true;
        this.contadorTempo = 2; // Tempo para o estado de atenção
    }

    public boolean estaAberto() {
        return this.aberto;
    }

    public boolean estaFechado() {
        return this.fechado;
    }

    public String getTipo() {
        return tipo;
    }

    public void mudaTipo(String novoTipo) {
        this.tipo = novoTipo;
    }

    public int getTempoAberto() {
        return tempoAberto;
    }

    public void setTempoAberto(int tempoAberto) {
        this.tempoAberto = tempoAberto;
    }

    public int getTempoFechado() {
        return tempoFechado;
    }

    public void setTempoFechado(int tempoFechado) {
        this.tempoFechado = tempoFechado;
    }

    public int getContadorTempo() {
        return contadorTempo;
    }

    public void atualizaContador() {
        if (contadorTempo > 0) {
            contadorTempo--;
        } else {
            if (this.aberto) {
                setAtencao();
            } else if (this.atencao) {
                fecha();
            } else {
                abre();
            }
        }
    }

    public void desenha(Graphics g, int x, int y) {
        if (this.tipo.equals("digital")) {
            // Desenha o semáforo digital
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 100, 200); // Corpo do semáforo

            g.setFont(new Font("Digital-7", Font.BOLD, 70));
            g.setColor(Color.WHITE);
            g.drawString(String.format("%02d", contadorTempo), x + 10, y + 120); // Cronômetro

            // Desenha o semáforo tradicional ao lado
            x += 120;
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 60, 160); // Corpo do semáforo

            if (this.aberto) {
                g.setColor(Color.GREEN);
                g.fillOval(x + 15, y + 10, 30, 30); // Luz verde
            } else if (this.atencao) {
                g.setColor(Color.YELLOW);
                g.fillOval(x + 15, y + 60, 30, 30); // Luz amarela
            } else {
                g.setColor(Color.RED);
                g.fillOval(x + 15, y + 110, 30, 30); // Luz vermelha
            }
        } else {
            // Desenha o semáforo tradicional
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 60, 160); // Corpo do semáforo

            if (this.aberto) {
                g.setColor(Color.GREEN);
                g.fillOval(x + 15, y + 10, 30, 30); // Luz verde
            } else if (this.atencao) {
                g.setColor(Color.YELLOW);
                g.fillOval(x + 15, y + 60, 30, 30); // Luz amarela
            } else {
                g.setColor(Color.RED);
                g.fillOval(x + 15, y + 110, 30, 30); // Luz vermelha
            }
        }
    }
}
