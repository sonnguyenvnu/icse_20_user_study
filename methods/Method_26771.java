private String standardizeAnnotation(String fullAnnotationName,String patternName){
  String annotationName=fullAnnotationName.endsWith(".class") ? fullAnnotationName.substring(0,fullAnnotationName.length() - ".class".length()) : fullAnnotationName;
  if (annotationName.equals(SuppressWarnings.class.getName())) {
    annotationName=SuppressWarnings.class.getSimpleName() + "(\"" + patternName + "\")";
  }
  return "`@" + annotationName + "`";
}
