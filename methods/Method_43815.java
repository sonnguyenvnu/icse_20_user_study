public String sign(Long nonce){
  return provider.getSignature(nonce);
}
