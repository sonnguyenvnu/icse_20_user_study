private Status getTarget(){
  if (target == null) {
    try {
      target=factory.createStatus(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
