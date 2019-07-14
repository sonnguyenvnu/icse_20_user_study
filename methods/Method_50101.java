/** 
 * Await termination on the main thread since the grpc library uses daemon threads. 
 */
private void blockUntilShutdown() throws InterruptedException {
  if (server != null) {
    server.awaitTermination();
  }
}
