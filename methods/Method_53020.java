@Override public void getAvailableTrends(){
  getDispatcher().invokeLater(new AsyncTask(AVAILABLE_TRENDS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Location> locations=twitter.getAvailableTrends();
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotAvailableTrends(locations);
        }
 catch (        Exception e) {
          logger.warn("Exception at getAvailableTrends",e);
        }
      }
    }
  }
);
}
