public BufferLayoutBuilder activate(Object tokenIdentity){
  BasicToken token=new BasicToken(tokenIdentity);
  if (!myRootTokens.contains(token) && !myChildToParentTokens.containsKey(token)) {
    throw new IllegalArgumentException("Can't activate unknown token:" + tokenIdentity);
  }
  myInitial=token;
  return this;
}
