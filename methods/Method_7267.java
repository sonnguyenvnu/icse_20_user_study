private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject cryptoObject){
  if (cryptoObject == null) {
    return null;
  }
 else   if (cryptoObject.getCipher() != null) {
    return new FingerprintManager.CryptoObject(cryptoObject.getCipher());
  }
 else   if (cryptoObject.getSignature() != null) {
    return new FingerprintManager.CryptoObject(cryptoObject.getSignature());
  }
 else   if (cryptoObject.getMac() != null) {
    return new FingerprintManager.CryptoObject(cryptoObject.getMac());
  }
 else {
    return null;
  }
}
