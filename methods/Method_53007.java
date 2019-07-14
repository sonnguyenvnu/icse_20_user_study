@Override public void lookup(final long... ids){
  getDispatcher().invokeLater(new AsyncTask(RETWEET_STATUS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Status> statuses=twitter.lookup(ids);
      for (      TwitterListener listener : listeners) {
        try {
          listener.lookedup(statuses);
        }
 catch (        Exception e) {
          logger.warn("Exception at lookup",e);
        }
      }
    }
  }
);
}
