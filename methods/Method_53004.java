@Override public void getRetweetsOfMe(final Paging paging){
  getDispatcher().invokeLater(new AsyncTask(RETWEETS_OF_ME,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Status> statuses=twitter.getRetweetsOfMe(paging);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotRetweetsOfMe(statuses);
        }
 catch (        Exception e) {
          logger.warn("Exception at getRetweetsOfMe",e);
        }
      }
    }
  }
);
}
