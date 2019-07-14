private IDs getTarget(){
  if (target == null) {
    try {
      target=factory.createIDs(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
