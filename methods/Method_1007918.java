@Override public void initialize(){
  loggerFactory=new Log4jLoggerFactory();
  markerFactory=new BasicMarkerFactory();
  mdcAdapter=new Log4jMDCAdapter();
}
