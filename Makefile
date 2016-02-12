build:
	@gradle installDist

run:
	@build/install/liminal/bin/liminal

wrapper:
	@gradle wrapper --gradle-version 2.10
