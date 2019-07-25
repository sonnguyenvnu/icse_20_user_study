private void init(Config config,boolean acceptAllCerts){
  this.config=config;
  this.transport=new HttpTransportImpl(this,acceptAllCerts);
  this.digitalSTROMClient=new DsAPIImpl(transport);
  if (this.genAppToken) {
    this.onNotAuthenticated();
  }
}
