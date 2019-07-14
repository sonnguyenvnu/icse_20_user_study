public static FCoinDigest createInstance(String secretKeyBase64){
  if (secretKeyBase64 != null) {
    return new FCoinDigest(Base64.getUrlDecoder().decode(secretKeyBase64.getBytes()));
  }
  return null;
}
