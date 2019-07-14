public static MocoJunitRunner restRunner(final RestServer server){
  checkNotNull(server,"Server should not be null");
  return httpRunner(server);
}
