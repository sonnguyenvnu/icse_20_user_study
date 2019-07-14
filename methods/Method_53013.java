@Override public void getMutesList(final long cursor){
  getDispatcher().invokeLater(new AsyncTask(MUTE_LIST,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<User> users=twitter.getMutesList(cursor);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotMutesList(users);
        }
 catch (        Exception e) {
          logger.warn("Exception at getMutesList",e);
        }
      }
    }
  }
);
}
