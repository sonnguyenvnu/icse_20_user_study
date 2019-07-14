private Relationship getTarget(){
  if (target == null) {
    try {
      target=factory.createRelationship(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
