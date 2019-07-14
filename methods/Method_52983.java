ResponseList<T> getTarget(){
  if (target == null) {
    try {
      target=createActualResponseList();
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
