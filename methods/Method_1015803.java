@Override public void log(String s){
  log.info(SecurityUtils.encode(s));
}
