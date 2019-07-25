public void setup(List<ProtocolConfiguration> configs) throws Exception {
  if (top_prot == null) {
    top_prot=new Configurator(this).setupProtocolStack(configs);
    top_prot.setUpProtocol(this);
    this.setDownProtocol(top_prot);
    bottom_prot=getBottomProtocol();
    initProtocolStack();
  }
}
