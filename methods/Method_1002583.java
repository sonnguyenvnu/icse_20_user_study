public void recycle(Request old) throws SSLException {
  super.recycle(old);
  this.engine=((HttpsRequest)old).engine;
  this.handshaken=true;
  wrapRequest();
}
