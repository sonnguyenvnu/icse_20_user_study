@Setup public void start() throws Exception {
  server=new ServerBuilder().https(0).service("/empty",(ctx,req) -> HttpResponse.of("\"\"")).tlsSelfSigned().build();
  server.start().join();
  client=newClient();
}
