private DirectMessage getTarget(){
  if (target == null) {
    try {
      target=factory.createDirectMessage(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
