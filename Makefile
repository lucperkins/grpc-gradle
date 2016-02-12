GRADLE=./gradlew

build-main:
	@${GRADLE} installApp

run-main:
	@build/install/liminal/bin/liminal ${ARGS}

example-main:
	@${MAKE} build-main
	@${MAKE} run-main ARGS="PROD"

wrapper:
	@gradle wrapper --gradle-version 2.10

test:
	@${GRADLE} test
