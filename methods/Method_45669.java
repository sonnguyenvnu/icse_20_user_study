@Override public boolean removeClientTransport(ClientTransport clientTransport){
  if (clientTransport == null) {
    return false;
  }
  allTransports.remove(clientTransport.getConfig());
  return true;
}
