@Override public void getIncomingFriendships(final long cursor){
  getDispatcher().invokeLater(new AsyncTask(INCOMING_FRIENDSHIPS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      IDs ids=twitter.getIncomingFriendships(cursor);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotIncomingFriendships(ids);
        }
 catch (        Exception e) {
          logger.warn("Exception at getIncomingFriendships",e);
        }
      }
    }
  }
);
}
