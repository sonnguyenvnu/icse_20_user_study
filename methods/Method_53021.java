@Override public void getClosestTrends(final GeoLocation location){
  getDispatcher().invokeLater(new AsyncTask(CLOSEST_TRENDS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Location> locations=twitter.getClosestTrends(location);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotClosestTrends(locations);
        }
 catch (        Exception e) {
          logger.warn("Exception at getClosestTrends",e);
        }
      }
    }
  }
);
}
