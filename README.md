# gRPC Demo App

## Working

This is just a basic app developed just to get an idea of gRPC and ProtoBufs. There are three things you can do with the app. 

- Create a student record. (File is saved in the resources folder with .txt extension)
- Modify contentrs of a student file.
- Read the contents of a file

## Running the app

#### Prerequisites
- Java (v 8+)
- Gradle

#### Commands to run the app
- Run `gradle build` to build the proto files and neccessary classes/
- Run `./gradlew installDist` to setup the task binaries
- To run server `./build/install/gRPC-demo/bin/app-server`
- To run client `./build/install/gRPC-demo/bin/app-client`
- You are good to go. Just follow the instructions on client side.

