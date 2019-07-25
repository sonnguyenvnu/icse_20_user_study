@Modified protected void modified(Map<String,Object> parameters){
  getConfigParameters(parameters);
  if (threadPool != null) {
    threadPool.setMinThreads(minThreadsShared);
    threadPool.setMaxThreads(maxThreadsShared);
    threadPool.setIdleTimeout(keepAliveTimeoutShared * 1000);
  }
}
