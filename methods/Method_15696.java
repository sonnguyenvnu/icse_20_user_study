private TwoFactorTokenInfo getTokenInfo(String userId,String operation){
  return Optional.ofNullable(tokens.get(createTokenInfoKey(userId,operation))).map(WeakReference::get).orElse(null);
}
