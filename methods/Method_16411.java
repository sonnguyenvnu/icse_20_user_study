@Override public AccessTokenInfo createToken(){
  return entityFactory.newInstance(AccessTokenInfo.class);
}
