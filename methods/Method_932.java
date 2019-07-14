public static boolean isGenerated(String content){
  int classIndex=content.indexOf(CLASS);
  if (classIndex <= 1) {
    return false;
  }
  String importHeader=content.substring(0,classIndex);
  return importHeader.contains(ANNOTATION_NAME);
}
