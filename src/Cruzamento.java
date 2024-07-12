import java.util.ArrayList;
import java.util.List;

public class Cruzamento {
    private List<Semaforo> semaforos;

    public Cruzamento() {
        this.semaforos = new ArrayList<>();
    }

    public int qtdSemaforos() {
        return semaforos.size();
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public void adicionaSemaforo(Semaforo semaforo) {
        this.semaforos.add(semaforo);
    }

    public void removeSemaforos() {
        this.semaforos.clear();
    }

    public void sincroniza() {
        if (semaforos.size() < 2) {
            throw new IllegalStateException("O cruzamento deve ter pelo menos 2 semáforos.");
        }

        Semaforo semaforo1 = semaforos.get(0);
        Semaforo semaforo2 = semaforos.get(1);

        new Thread(() -> {
            boolean semaforo1Aberto = true;
            while (true) {
                if (semaforo1Aberto) {
                    semaforo1.abre();
                    semaforo2.fecha();
                } else {
                    semaforo1.fecha();
                    semaforo2.abre();
                }
                semaforo1Aberto = !semaforo1Aberto;

                for (int i = 0; i < semaforo1.getTempoAberto(); i++) {
                    semaforo1.atualizaContador();
                    semaforo2.atualizaContador();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                semaforo1.setAtencao();
                for (int i = 0; i < 2; i++) {
                    semaforo1.atualizaContador();
                    semaforo2.atualizaContador();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                semaforo1.fecha();
                semaforo2.abre();

                for (int i = 0; i < semaforo2.getTempoFechado(); i++) {
                    semaforo1.atualizaContador();
                    semaforo2.atualizaContador();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                semaforo2.setAtencao();
                for (int i = 0; i < 2; i++) {
                    semaforo1.atualizaContador();
                    semaforo2.atualizaContador();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int totalDeSemaforos() {
        return this.semaforos.size();
    }
}
