private static ClassName getComponentTypeName(String componentClassName,String qualifiedSpecClassName){
  final String qualifiedComponentClassName;
  if (componentClassName == null || componentClassName.isEmpty()) {
    qualifiedComponentClassName=qualifiedSpecClassName.substring(0,qualifiedSpecClassName.length() - SPEC_SUFFIX.length());
  }
 else {
    qualifiedComponentClassName=qualifiedSpecClassName.substring(0,qualifiedSpecClassName.lastIndexOf('.') + 1) + componentClassName;
  }
  return ClassName.bestGuess(qualifiedComponentClassName);
}
