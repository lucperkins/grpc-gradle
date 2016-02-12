GRADLE=./gradlew
DOCS_DIR=build/docs/javadoc

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

docs:
	@gradle javadoc
	@open ${DOCS_DIR}/index.html
