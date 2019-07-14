public static Runner runner(final HttpServer server){
  return new MocoHttpServer((ActualHttpServer)checkNotNull(server,"Server should not be null"));
}
