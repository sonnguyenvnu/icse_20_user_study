@Override public void remove(ProxyModel proxyModel){
  ProxyMonitorCollector result=proxySamples.remove(proxyModel);
  if (result != null) {
    try {
      result.removeListener(proxyInfoRecorder);
      result.stop();
    }
 catch (    Exception e) {
      logger.error("[remove]",e);
    }
  }
}
