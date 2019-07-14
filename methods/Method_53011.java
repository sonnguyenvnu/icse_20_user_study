@Override public void getOutgoingFriendships(final long cursor){
  getDispatcher().invokeLater(new AsyncTask(OUTGOING_FRIENDSHIPS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      IDs ids=twitter.getOutgoingFriendships(cursor);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotOutgoingFriendships(ids);
        }
 catch (        Exception e) {
          logger.warn("Exception at getOutgoingFriendships",e);
        }
      }
    }
  }
);
}
