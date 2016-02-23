# grpc-gradle

This is basic project template for people wanting to use the
[Gradle](http://gradle.org/) build tool with Google's
[gRPC](http://www.grpc.io/) framework.

Here I've implemented a client and server based on the [Protocol
Buffers](https://developers.google.com/protocol-buffers/) definition file in
`src/main/proto/auth.proto`.

## Using

To build the client and server:

```bash
$ gradle installDist
```

To run the server:

```bash
$ build/install/grpc-gradle/bin/auth-server
```

To run the client you must specify a username and password as args 0 and 1:

```bash
$ build/install/grpc-gradle/bin/auth-client {username} {password}
```

If you don't specify `tonydanza` as the username and `whostheboss` as the
password your authenticate request will fail!

## What Lives Where

When you run `gradle build` and analogous commands, Gradle will place all
generated Java files in `src/generated/main/{grpc,java}`.

The executables for the client and server are defined in
`src/main/resources/executables.gradle`. The Gradle configuration for gRPC and
Protocol Buffers are found in `src/main/resources/grpc.gradle`.

# Versions

I did all of this using Gradle 2.10.0 version 0.12.0 of the [Java gRPC
library](http://search.maven.org/#artifactdetails%7Cio.grpc%7Cgrpc-all%7C0.13.0%7Cjar).

## What's the Purpose of This?

The example client and server are heavily based on the examples on the
[grpc-java](https://github.com/grpc/grpc-java) GitHub page and you *can* piece
together how to do all of this stuff. But it's a pain in the ass and it took me
a while to work out a bunch of kinks, so I figured I'd share it with the world.
