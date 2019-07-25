@Override public void block(){
  try {
    state.set(BLOCK);
    try {
      if (store == null)       return;
      store.block(this);
    }
 catch (    Exception e) {
      TraceUtil.recordEvent("Qmq.Store.Failed");
    }
    LOGGER.info("?????");
    if (store == null && syncSend) {
      throw new RuntimeException("????????store???,?????????");
    }
  }
  finally {
    onFailed();
    closeTrace();
  }
}
