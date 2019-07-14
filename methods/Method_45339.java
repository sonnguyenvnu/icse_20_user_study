public static MocoJunitRunner jsonHttpRunner(final int port,final Resource file){
  checkArgument(port > 0,"Port must be greater than zero");
  checkNotNull(file,"File should not be null");
  return new MocoJunitRunner(runner(jsonHttpServer(port,file)));
}
