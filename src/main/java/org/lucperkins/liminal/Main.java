package org.lucperkins.liminal;

public class Main {
    private static class Run implements Runnable {
        @Override public void run() {
            System.out.println("Hello world");
        }
    }

    public static void main(String[] args) {
        new Run().run();
    }
}
