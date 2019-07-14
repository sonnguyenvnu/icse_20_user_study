public JanusGraphTransaction getCurrentThreadTx(){
  return getAutoStartTx();
}
