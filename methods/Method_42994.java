public long getTimestamp() throws IOException {
  long deltaServerTime=((BinanceExchange)exchange).deltaServerTime();
  Date systemTime=new Date(System.currentTimeMillis());
  Date serverTime=new Date(systemTime.getTime() + deltaServerTime);
  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
  LOG.trace("getTimestamp: {} + {} => {}",df.format(systemTime),deltaServerTime,df.format(serverTime));
  return serverTime.getTime();
}
