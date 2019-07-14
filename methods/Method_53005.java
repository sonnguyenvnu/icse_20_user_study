@Override public void getRetweets(final long statusId){
  getDispatcher().invokeLater(new AsyncTask(RETWEETS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Status> statuses=twitter.getRetweets(statusId);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotRetweets(statuses);
        }
 catch (        Exception e) {
          logger.warn("Exception at getRetweets",e);
        }
      }
    }
  }
);
}
