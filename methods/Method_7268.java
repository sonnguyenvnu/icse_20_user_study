private static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject cryptoObject){
  if (cryptoObject == null) {
    return null;
  }
 else   if (cryptoObject.getCipher() != null) {
    return new CryptoObject(cryptoObject.getCipher());
  }
 else   if (cryptoObject.getSignature() != null) {
    return new CryptoObject(cryptoObject.getSignature());
  }
 else   if (cryptoObject.getMac() != null) {
    return new CryptoObject(cryptoObject.getMac());
  }
 else {
    return null;
  }
}
