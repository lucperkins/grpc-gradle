package org.grpcgradle.auth;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.io.InterruptedIOException;

public class AuthServer {
    private final int PORT = 8888;
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
            boolean authenticated;

            authenticated =
                    (request.getUsername().equals("tonydanza") && request.getPassword().equals("whostheboss"));

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
            authServer.start();
            authServer.blockUntilShutdown();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
