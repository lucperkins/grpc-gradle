package org.grpcgradle.auth;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.io.InterruptedIOException;

public class AuthServer {
    private final static int PORT = 8888;
    private Server grpcServer;

    private void start() throws IOException {
        grpcServer = ServerBuilder.forPort(PORT)
                .addService(AuthServiceGrpc.bindService(new AuthServerImpl()))
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                AuthServer.this.stop();
            }
        });
    }

    public void stop() {
        if (grpcServer != null) grpcServer.shutdown();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (grpcServer != null) grpcServer.awaitTermination();
    }

    private class AuthServerImpl implements AuthServiceGrpc.AuthService {
        @Override
        public void authenticate(AuthRequest request, StreamObserver<AuthResponse> responseObserver) {
            String username = request.getUsername();
            String password = request.getPassword();

            System.out.print(String.format("Request received:\nusername: %s\npassword: %s\n", username, password));

            boolean authenticated;

            authenticated =
                    (username.equals("tonydanza") && password.equals("whostheboss"));

            System.out.println(String.format("authenticated: %s", authenticated));

            AuthResponse response = AuthResponse.newBuilder()
                    .setAuthenticated(authenticated)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) {
        final AuthServer authServer = new AuthServer();
        try {
            System.out.println("Starting server...");
            authServer.start();
            System.out.println(String.format("The server is now running on port %s", PORT));
            authServer.blockUntilShutdown();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
