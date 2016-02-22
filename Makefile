GRADLE=./gradlew

run-auth-server:
	@${GRADLE} installDist
	@${GRADLE} authServer
	@build/install/grpc-gradle/bin/auth-server

run-auth-client:
	@${GRADLE} installDist
	@${GRADLE} authClient
	@build/install/grpc-gradle/bin/auth-client
