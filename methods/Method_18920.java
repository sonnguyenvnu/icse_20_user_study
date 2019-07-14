private static String getSpecName(String qualifiedSpecClassName){
  return qualifiedSpecClassName.substring(qualifiedSpecClassName.lastIndexOf('.') + 1);
}
