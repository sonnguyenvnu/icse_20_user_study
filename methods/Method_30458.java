@Override public User getUser(Account account){
  return mUserResourceMap.get(account).get();
}
