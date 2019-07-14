@Override public boolean validateObject(String key,CTConnection c){
  Config curCfg=cfgRef.get();
  boolean isSameConfig=c.getConfig().equals(curCfg);
  if (log.isDebugEnabled()) {
    if (isSameConfig) {
      log.trace("Validated {} by configuration {}",c,curCfg);
    }
 else {
      log.trace("Rejected {}; current config is {}; rejected connection config is {}",c,curCfg,c.getConfig());
    }
  }
  boolean isOpen=c.isOpen();
  try {
    c.getClient().describe_version();
  }
 catch (  TException e) {
    isOpen=false;
  }
  return isSameConfig && isOpen;
}
