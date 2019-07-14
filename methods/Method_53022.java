@Override public void getAPIConfiguration(){
  getDispatcher().invokeLater(new AsyncTask(CONFIGURATION,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      TwitterAPIConfiguration apiConf=twitter.getAPIConfiguration();
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotAPIConfiguration(apiConf);
        }
 catch (        Exception e) {
          logger.warn("Exception at getAPIConfiguration",e);
        }
      }
    }
  }
);
}
