public static YoBitDigest createInstance(String secretKeyBase64,String apiKey){
  try {
    String checkedSecretKeyBase64=secretKeyBase64 != null ? secretKeyBase64 : getRandomSecretKey();
    return new YoBitDigest(checkedSecretKeyBase64,apiKey);
  }
 catch (  Exception e) {
    throw new IllegalStateException("cannot create digest",e);
  }
}
