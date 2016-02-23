# grpc-gradle

This is basic project template for people wanting to use the
[Gradle](http://gradle.org/) build tool with Google's
[gRPC](http://www.grpc.io/) framework.

Here I've implemented a client and server based on the [Protocol
Buffers](https://developers.google.com/protocol-buffers/) definition file in
`src/main/proto/auth.proto`.

## Using

To run the server:

```bash
$ make run-auth-server
```

To run the client:

```bash
$ make run-auth-client
```

## What Lives Where

When you run `gradle build` and analogous commands, Gradle will place all
generated Java files in `src/generated/main/{grpc,java}`.

The executables for the client and server are defined in
`src/main/resources/executables.gradle`. The Gradle configuration for gRPC and
Protocol Buffers are found in `src/main/resources/grpc.gradle`.

## What's the Purpose of This?

The example client and server are heavily based on the examples on the
[grpc-java](https://github.com/grpc/grpc-java) GitHub page and you *can* piece
together how to do all of this stuff. But it's a pain in the ass and it took me
a while to work out a bunch of kinks, so I figured I'd share it with the world.
