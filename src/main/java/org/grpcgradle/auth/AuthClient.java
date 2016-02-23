package org.grpcgradle.auth;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class AuthClient {
    private final String HOST = "localhost";
    private final int PORT = 8888;
    private final ManagedChannel channel;
    private final AuthServiceGrpc.AuthServiceBlockingStub blockingStub;

    public AuthClient() {
        channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();
        blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public Optional<Boolean> authenticate(String username, String password) {
        AuthRequest request = AuthRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        AuthResponse response;
        try {
            response = blockingStub.authenticate(request);
            return Optional.of(response.getAuthenticated());
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Building client...");
        AuthClient client = new AuthClient();
        try {
            String USERNAME = "tonydanza";
            String PASSWORD = "whostheboss";
            System.out.println("Sending message...");
            Optional<Boolean> response = client.authenticate(USERNAME, PASSWORD);
            String message = (response.isPresent()) ? String.format("Response: %s", response.get()) : "Something went wrong";
            System.out.println(message);
        } finally {
            System.out.println("The client is shutting down");
            client.shutdown();
        }
    }
}
