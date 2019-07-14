public String createVerifier(){
  byte[] verifierBytes=new byte[getVerifierLengthBytes()];
  getRandom().nextBytes(verifierBytes);
  return getVerifierString(verifierBytes);
}
