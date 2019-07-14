@C.CryptoMode private static int schemeToCryptoMode(@Nullable String schemeType){
  if (schemeType == null) {
    return C.CRYPTO_MODE_AES_CTR;
  }
switch (schemeType) {
case C.CENC_TYPE_cenc:
case C.CENC_TYPE_cens:
    return C.CRYPTO_MODE_AES_CTR;
case C.CENC_TYPE_cbc1:
case C.CENC_TYPE_cbcs:
  return C.CRYPTO_MODE_AES_CBC;
default :
Log.w(TAG,"Unsupported protection scheme type '" + schemeType + "'. Assuming AES-CTR " + "crypto mode.");
return C.CRYPTO_MODE_AES_CTR;
}
}
