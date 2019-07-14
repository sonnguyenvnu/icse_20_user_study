@Override public void lookupFriendships(final String... screenNames){
  getDispatcher().invokeLater(new AsyncTask(LOOKUP_FRIENDSHIPS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Friendship> friendships=twitter.lookupFriendships(screenNames);
      for (      TwitterListener listener : listeners) {
        try {
          listener.lookedUpFriendships(friendships);
        }
 catch (        Exception e) {
          logger.warn("Exception at lookupFriendships",e);
        }
      }
    }
  }
);
}
