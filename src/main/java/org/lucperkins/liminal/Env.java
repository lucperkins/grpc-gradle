package org.lucperkins.liminal;

enum Env {
    DEV,
    PROD;

    static Env fromString(String s) {
        switch (s.toLowerCase()) {
            case "prod":
                return PROD;
            case "dev":
                return DEV;
            default:
                return DEV;
        }
    }

    static Env fromArgs(String[] args) {
        if (args == null || args.length == 0) {
            return DEV;
        } else {
            return fromString(args[0]);
        }
    }
}