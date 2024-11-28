package tr.com.otunctan.dp.structural.facade;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineHeight * lineWidth];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class Viewport {

    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;

    public Viewport(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buffer.charAt(x + offsetX, y + offsetY);
    }

}

class Console {
    private List<Viewport> viewports = new ArrayList<>();
    private final int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Console newConsole(int w, int h) {
        Buffer buffer = new Buffer(w, h);
        Viewport vp = new Viewport(buffer, w, h, 0, 0);
        Console console = new Console(w, h);
        console.addViewPort(vp);
        return console;
    }

    public void addViewPort(Viewport viewport) {
        viewports.add(viewport);
    }

    public void render() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; x++) {
                for (Viewport vp : viewports) {
                    System.out.println(vp.charAt(x, y));
                }
                System.out.println();
            }
        }
    }

}

class Demo {
    public static void main(String[] args) {
//        Buffer buffer = new Buffer(30, 20);
//        Viewport vp = new Viewport(buffer, 30, 20, 0, 0);
//        Console console = new Console(30, 20);
//        console.addViewPort(vp);
//        console.render();

        Console console = Console.newConsole(30, 20);
        console.render();

    }
}