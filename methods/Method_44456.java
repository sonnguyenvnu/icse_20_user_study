public static KrakenDigest createInstance(String secretKeyBase64){
  if (secretKeyBase64 != null) {
    return new KrakenDigest(Base64.getDecoder().decode(secretKeyBase64.getBytes()));
  }
 else   return null;
}
