@Override public void _XXXXX_(int rc,String path,Object ctx,List<String> children,Stat stat){
  if (KeeperException.Code.OK.intValue() == rc) {
    logger.info("Received updated logs under {} : {}",uri,children);
    List<String> result=new ArrayList<String>(children.size());
    for (    String s : children) {
      if (isReservedStreamName(s)) {
        continue;
      }
      result.add(s);
    }
    for (    NamespaceListener listener : listeners) {
      listener.onStreamsChanged(result.iterator());
    }
  }
 else {
    scheduleTask(this,conf.getZKSessionTimeoutMilliseconds());
  }
}