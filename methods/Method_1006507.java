public AliPayApiConfig build(){
  this.alipayClient=new DefaultAlipayClient(getServiceUrl(),getAppId(),getPrivateKey(),getFormat(),getCharset(),getAlipayPublicKey(),getSignType());
  return this;
}
