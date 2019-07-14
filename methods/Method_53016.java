@Override public void getContributees(final String screenName){
  getDispatcher().invokeLater(new AsyncTask(CONTRIBUTEEES,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<User> users=twitter.getContributors(screenName);
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
