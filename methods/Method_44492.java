protected void checkAuthenticated(){
  if (Strings.isNullOrEmpty(this.apiKey))   throw new KucoinApiException("Missing API key");
  if (this.digest == null)   throw new KucoinApiException("Missing secret key");
  if (Strings.isNullOrEmpty(this.passphrase))   throw new KucoinApiException("Missing passphrase");
}
