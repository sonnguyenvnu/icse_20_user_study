private String getResultSetVariableName(String image){
  if (image.contains(".")) {
    for (    String method : methods) {
      if (image.endsWith(method)) {
        return image.substring(0,image.lastIndexOf(method));
      }
    }
  }
  return null;
}
