@Override public JanusGraphQuery<? extends JanusGraphQuery> query(){
  return getAutoStartTx().query();
}
