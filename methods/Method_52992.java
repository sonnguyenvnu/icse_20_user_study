private Trends getTarget(){
  if (target == null) {
    try {
      target=factory.createTrends(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
