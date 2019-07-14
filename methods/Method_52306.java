private String trimIfPrefix(String img){
  if (prefixProperty != null && img.startsWith(prefixProperty)) {
    return img.substring(prefixProperty.length());
  }
  return img;
}
