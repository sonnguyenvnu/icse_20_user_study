@Override public void verify(byte[] content,byte[] sig){
  try {
    Signature signature=Signature.getInstance(this.algorithm);
    signature.initVerify(this.key);
    signature.update(content);
    if (!signature.verify(EllipticCurveSignatureHelper.transcodeSignatureToDER(sig))) {
      throw new InvalidSignatureException("EC Signature did not match content");
    }
  }
 catch (  GeneralSecurityException ex) {
    throw new RuntimeException(ex);
  }
}
