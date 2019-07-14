private void flush(){
  TokenExpiry expiry=expiryQueue.poll();
  while (expiry != null) {
    removeAccessToken(expiry.getValue());
    expiry=expiryQueue.poll();
  }
}
