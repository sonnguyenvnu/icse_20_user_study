protected RouterStub find(IpAddress router_addr){
  for (  RouterStub stub : stubs) {
    IpAddress addr=stub.gossipRouterAddress();
    if (Objects.equals(addr,router_addr))     return stub;
  }
  return null;
}
