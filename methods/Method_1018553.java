@JsMethod static SymmetricKey random(){
  return TweetNaClKey.random(PROVIDERS.get(Type.TweetNaCl),RNG_PROVIDERS.get(Type.TweetNaCl));
}
