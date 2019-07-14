private UserList getTarget(){
  if (target == null) {
    try {
      target=factory.createAUserList(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
