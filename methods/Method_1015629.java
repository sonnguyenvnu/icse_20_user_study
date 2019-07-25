public void setup(ProtocolStack stack) throws Exception {
  if (top_prot == null) {
    top_prot=new Configurator(this).setupProtocolStack(stack);
    top_prot.setUpProtocol(this);
    this.setDownProtocol(top_prot);
    bottom_prot=getBottomProtocol();
    initProtocolStack();
  }
}
