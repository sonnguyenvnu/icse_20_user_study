@Override public void run(){
  for (  Map.Entry<MetaInfoRequestParam,Integer> entry : metaInfoRequests.entrySet()) {
    requestWrapper(entry.getKey());
  }
}
