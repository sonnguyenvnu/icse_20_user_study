private static boolean isVariable(String templatePart){
  return PATH_VARIABLE_REGEX.matcher(templatePart).matches();
}
