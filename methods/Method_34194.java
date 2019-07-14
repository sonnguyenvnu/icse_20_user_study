public void setExpriation(Date expiration){
  if (expiration == null) {
    setExpiresIn(null);
    return;
  }
  long now=System.currentTimeMillis();
  setExpiresIn((expiration.getTime() - now) / 1000);
}
