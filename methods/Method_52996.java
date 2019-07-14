private User getTarget(){
  if (target == null) {
    try {
      target=new UserJSONImpl(res,conf);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
