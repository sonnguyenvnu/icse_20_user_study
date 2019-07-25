@Nonnull @Override public NiFiFlowVisitorClient flows(){
  if (flows == null) {
    flows=new DefaultNiFiFlowVisitorClient(this);
  }
  return flows;
}
