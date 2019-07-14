private String findQualifiedName(String typeImage,Set<String> candidates){
  int nameLength=typeImage.length();
  for (  String qualified : candidates) {
    int fullLength=qualified.length();
    if (qualified.endsWith(typeImage) && (fullLength == nameLength || qualified.charAt(fullLength - nameLength - 1) == '.')) {
      return qualified;
    }
  }
  return null;
}
