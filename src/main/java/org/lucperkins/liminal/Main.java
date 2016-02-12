package org.lucperkins.liminal;

public class Main {
    static class Run implements Runnable {
        final Env env;

        Run(Env env) { this.env = env; }

        @Override public void run() {
            switch (env) {
                case DEV:
                    System.out.println(env);
                    break;
                case PROD:
                    System.out.println(env);
                    break;
                default:
                    System.out.println("SOMETHING WENT WRONG");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Env env = Env.fromArgs(args);
        new Run(env).run();
    }
}