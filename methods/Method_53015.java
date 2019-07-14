@Override public void getContributees(final long userId){
  getDispatcher().invokeLater(new AsyncTask(CONTRIBUTEEES,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<User> users=twitter.getContributors(userId);
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotContributees(users);
        }
 catch (        Exception e) {
          logger.warn("Exception at getContributees",e);
        }
      }
    }
  }
);
}
