public static BitmexDigest createInstance(String secretKeyBase64){
  if (secretKeyBase64 != null) {
    return new BitmexDigest(Base64.getUrlDecoder().decode(secretKeyBase64.getBytes()));
  }
  return null;
}
