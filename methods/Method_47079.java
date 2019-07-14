protected void initSignatureFactories(){
  setSignatureFactories(new SignatureRSA.Factory(),new SignatureDSA.Factory());
}
