public boolean calls(){
  boolean isRet=false;
  try {
    this.callHttps();
    isRet=true;
  }
 catch (  UnrecoverableKeyException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  KeyManagementException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  CertificateException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  KeyStoreException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  NoSuchAlgorithmException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  IOException e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
catch (  Exception e) {
    _log.error("",e);
    this.errInfo=e.getMessage();
  }
  return isRet;
}
