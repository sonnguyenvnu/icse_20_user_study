@Override public void createUserListSubscription(final long listId){
  getDispatcher().invokeLater(new AsyncTask(SUBSCRIBE_LIST,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      UserList list=twitter.createUserListSubscription(listId);
      for (      TwitterListener listener : listeners) {
        try {
          listener.subscribedUserList(list);
        }
 catch (        Exception e) {
          logger.warn("Exception at createUserListSubscription",e);
        }
      }
    }
  }
);
}
