private Function<HttpsArg,HttpsCertificate> toCertificate(){
  return new Function<HttpsArg,HttpsCertificate>(){
    @Override public HttpsCertificate apply(    final HttpsArg input){
      return input.getCertificate();
    }
  }
;
}
