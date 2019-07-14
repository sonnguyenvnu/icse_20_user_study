private String methodAndClassNamePatternGroupOrDefault(int group,String defaultString){
  Matcher matcher=METHOD_AND_CLASS_NAME_PATTERN.matcher(toString());
  return matcher.matches() ? matcher.group(group) : defaultString;
}
