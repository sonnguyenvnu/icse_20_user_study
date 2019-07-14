public static String convertToLowerUnderscore(String identifierName){
  return splitToLowercaseTerms(identifierName).stream().collect(Collectors.joining("_"));
}
