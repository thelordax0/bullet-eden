package org.thelordax0.bulleteden;

import org.thelordax0.bulleteden.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public JFrame frame;
    private Thread thread;
    private boolean running = false;
    private BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    private int[] pixels=  ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    private Screen screen;
    public Game() {

        Dimension size = new Dimension(width * scale, height * scale);
        this.setPreferredSize(size);

        screen=new Screen(width,height);

        frame = new JFrame();

    }

    public static void main(String[] args) {






        Game game = new Game();
        game.frame.add(game);
        game.frame.setTitle("Bullet Eden");
        game.frame.setLocationRelativeTo(null);
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.pack();
        game.frame.setVisible(true);
        game.start();


    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (running) {
            update();
            render();
        }
    }

    private void update() {
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g=bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.dispose();
        bs.show();

    }
}
