private String generateOperatorString(ContentPattern<?> pattern,String defaultValue){
  return isAnEqualToPattern(pattern) ? defaultValue : " [" + pattern.getName() + "] ";
}
