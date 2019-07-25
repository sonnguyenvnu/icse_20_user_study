@Override public void log(String s,Throwable throwable){
  log.error(SecurityUtils.encode(s),throwable);
}
