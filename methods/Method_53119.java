private String toResizedURL(String originalURL,String sizeSuffix){
  if (null != originalURL && originalURL.length() >= 1) {
    int index=originalURL.lastIndexOf("_");
    int suffixIndex=originalURL.lastIndexOf(".");
    int slashIndex=originalURL.lastIndexOf("/");
    String url=originalURL.substring(0,index) + sizeSuffix;
    if (suffixIndex > slashIndex) {
      url+=originalURL.substring(suffixIndex);
    }
    return url;
  }
  return null;
}
