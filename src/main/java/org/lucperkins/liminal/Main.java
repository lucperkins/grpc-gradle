package org.lucperkins.liminal;

public class Main {
    static class Run implements Runnable {
        final Env env;

        Run(Env env) { this.env = env; }

        @Override public void run() {
            System.out.println("Env: " + env.toString());
        }
    }

    public static void main(String[] args) {
        Env env = Env.fromArgs(args);
        new Run(env).run();
    }
}