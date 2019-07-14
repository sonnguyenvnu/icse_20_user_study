@Override public void showUserListSubscription(final long listId,final long userId){
  getDispatcher().invokeLater(new AsyncTask(CHECK_LIST_SUBSCRIPTION,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      User user=twitter.showUserListSubscription(listId,userId);
      for (      TwitterListener listener : listeners) {
        try {
          listener.checkedUserListSubscription(user);
        }
 catch (        Exception e) {
          logger.warn("Exception at showUserListSubscription",e);
        }
      }
    }
  }
);
}
