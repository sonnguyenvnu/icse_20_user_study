public static MocoJunitRunner jsonRestRunner(final int port,final Resource file){
  checkArgument(port > 0,"Port must be greater than zero");
  checkNotNull(file,"File should not be null");
  return jsonHttpRunner(port,file);
}
