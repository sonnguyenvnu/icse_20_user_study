private static String toSimpleType(String fullyQualifiedClassName){
  int lastIndexOf=fullyQualifiedClassName.lastIndexOf('.');
  if (lastIndexOf > -1) {
    return fullyQualifiedClassName.substring(lastIndexOf + 1);
  }
 else {
    return fullyQualifiedClassName;
  }
}
