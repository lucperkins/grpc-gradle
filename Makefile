GRADLE=./gradlew

build-main:
	@${GRADLE} installDist

run-main:
	@build/install/liminal/bin/liminal ${ARGS}

example-main:
	@${MAKE} run-main ARGS="PROD"

wrapper:
	@gradle wrapper --gradle-version 2.10

test:
	@${GRADLE} test
