@Override public int execute(final PrintWriter out,final PrintWriter err) throws Exception {
  final ExecDumpClient client=new ExecDumpClient(){
    @Override protected void onConnecting(    final InetAddress address,    final int port){
      out.printf("[INFO] Connecting to %s:%s.%n",address,Integer.valueOf(port));
    }
    @Override protected void onConnectionFailure(    final IOException exception){
      err.printf("[WARN] %s.%n",exception.getMessage());
    }
  }
;
  client.setReset(reset);
  client.setRetryCount(retrycount);
  final ExecFileLoader loader=client.dump(address,port);
  out.printf("[INFO] Writing execution data to %s.%n",destfile.getAbsolutePath());
  loader.save(destfile,true);
  return 0;
}
