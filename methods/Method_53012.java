@Override public void createBlock(final long userId){
  getDispatcher().invokeLater(new AsyncTask(CREATE_BLOCK,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      User user=twitter.createBlock(userId);
      for (      TwitterListener listener : listeners) {
        try {
          listener.createdBlock(user);
        }
 catch (        Exception e) {
          logger.warn("Exception at createBlock",e);
        }
      }
    }
  }
);
}
