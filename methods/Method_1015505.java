public void init() throws Exception {
  timer=getTransport().getTimer();
  if (timer == null)   throw new Exception("timer not set");
  suspected_mbrs.clear();
  has_suspected_mbrs=false;
}
