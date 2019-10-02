package View;

import Controller.Jogo;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

// checa a parte de desenho do projeto, porém, tenho que dar uma atualizada, pois há erros na parte de 2D;
public class Tap
{
    public Jogo jogo;
    public BufferStrategy strategy;

    public TreeSet<String> keySet = new TreeSet<String>();

    // Metodos gerados automaticamente pelo netbeans, trouxe para o intellij para modificações;
    public Tap(Jogo jogo) {

        this.jogo = jogo;
        Canvas canvas = new Canvas();
        JFrame container = new JFrame(this.jogo.getTitulo());
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(
                this.jogo.getLargura(), this.jogo.getAltura()));
        panel.setLayout(null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Rectangle bounds = gs[gs.length-1].getDefaultConfiguration().getBounds();
        container.setResizable(false);
        container.setBounds(bounds.x+(bounds.width - this.jogo.getLargura())/2,
                            bounds.y+(bounds.height - this.jogo.getAltura())/2,
                            this.jogo.getLargura(), this.jogo.getAltura());
        canvas.setBounds(0,0, this.jogo.getLargura(), this.jogo.getAltura());
        panel.add(canvas);        
        canvas.setIgnoreRepaint(true);
        container.pack();
        container.setVisible(true);
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                keySet.add(keyString(evt));
            }
            @Override
            public void keyReleased(KeyEvent evt) {
                keySet.remove(keyString(evt));
            }
            @Override
            public void keyTyped(KeyEvent evt) {
                Tap.this.jogo.tecla(keyString(evt));
            }
        });

        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        mainLoop();
    }

    // Metodo que releva o delay e manda as repeticoes para a classe FlappyBird;
    private void mainLoop() {

        Timer tempo = new Timer(5, new ActionListener() {
            public long Inicial0;
            public void actionPerformed(ActionEvent evt) {
                long Incial1 = System.currentTimeMillis();
                if(Inicial0 == 0)
                    Inicial0 = Incial1;
                if(Incial1 > Inicial0) {
                    double velocidadeMedia = (Incial1 - Inicial0) / 1000.0;
                    Inicial0 = Incial1;
                    jogo.tique(keySet, velocidadeMedia);
                    Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
                    g.setColor(Color.black);
                    g.fillRect(0,0,jogo.getLargura(),
                          jogo.getAltura());
                    jogo.tela(new Tela(g));
                    strategy.show();
                }
            }
        });
            
        tempo.start();
    }

    // Metodo estatico que ler as teclas pressionadas no teclado;
    private static String keyString(KeyEvent evt) {

        if(evt.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {

            return String.valueOf(evt.getKeyChar()).toLowerCase();
        } else {

            switch(evt.getKeyCode()) {
            case KeyEvent.VK_ALT: return "alt";
            case KeyEvent.VK_CONTROL: return "control";
            case KeyEvent.VK_SHIFT: return "shift";
            case KeyEvent.VK_LEFT: return "left";
            case KeyEvent.VK_RIGHT: return "right";
            case KeyEvent.VK_UP: return "up";
            case KeyEvent.VK_DOWN: return "down";
            case KeyEvent.VK_ENTER: return "enter";
            case KeyEvent.VK_DELETE: return "delete";
            case KeyEvent.VK_TAB: return "tab";
            case KeyEvent.VK_WINDOWS: return "windows";
            case KeyEvent.VK_BACK_SPACE: return "backspace";
            case KeyEvent.VK_ALT_GRAPH: return "altgr";
            case KeyEvent.VK_F1: return "F1";
            case KeyEvent.VK_F2: return "F2";
            case KeyEvent.VK_F3: return "F3";
            case KeyEvent.VK_F4: return "F4";
            case KeyEvent.VK_F5: return "F5";
            case KeyEvent.VK_F6: return "F6";
            case KeyEvent.VK_F7: return "F7";
            case KeyEvent.VK_F8: return "F8";
            case KeyEvent.VK_F9: return "F9";
            case KeyEvent.VK_F10: return "F10";
            case KeyEvent.VK_F11: return "F11";
            case KeyEvent.VK_F12: return "F12";
            default: return "";
            }
        }
    }

    // Metodo estatico que jogar sons, porém não consegui colocar, pois travava;
    public static void tocar(String filename) {

        try {

            Clip checar = AudioSystem.getClip();
            checar.open(AudioSystem.getAudioInputStream(new File(filename)));
            checar.start();
        } catch(Exception e) {

        }
    }

}
