public static CryptoFacilitiesDigest createInstance(String secretKeyBase64){
  if (secretKeyBase64 != null) {
    return new CryptoFacilitiesDigest(Base64.getDecoder().decode(secretKeyBase64.getBytes()));
  }
 else {
    return null;
  }
}
