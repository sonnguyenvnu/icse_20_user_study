@Override public void getMutesIDs(final long cursor){
  getDispatcher().invokeLater(new AsyncTask(MUTE_LIST_IDS,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      IDs ids=twitter.getMutesIDs(cursor);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotMuteIDs(ids);
        }
 catch (        Exception e) {
          logger.warn("Exception at getMutesIDs",e);
        }
      }
    }
  }
);
}
