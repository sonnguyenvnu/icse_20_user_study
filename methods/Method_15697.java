@Override public TwoFactorToken getToken(String userId,String operation){
  return new TwoFactorToken(){
    @Override public void generate(    long timeout){
      TwoFactorTokenInfo info=new TwoFactorTokenInfo();
      info.timeOut=timeout;
      tokens.put(createTokenInfoKey(userId,operation),new WeakReference<>(info));
    }
    @Override public boolean expired(){
      TwoFactorTokenInfo info=getTokenInfo(userId,operation);
      if (info == null) {
        return true;
      }
      if (info.isExpire()) {
        tokens.remove(createTokenInfoKey(userId,operation));
        return true;
      }
      info.lastRequestTime=System.currentTimeMillis();
      return false;
    }
  }
;
}
