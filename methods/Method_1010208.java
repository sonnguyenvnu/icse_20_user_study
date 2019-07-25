public ClientToken subscribe(@NotNull TraceClient client){
  ClientToken rv=new ClientToken();
  myActiveClients.add(rv);
  return rv;
}
