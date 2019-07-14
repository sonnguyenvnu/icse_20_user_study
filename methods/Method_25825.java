private static String upperCaseReplace(String name){
  String constName;
  if (name.contains("_")) {
    constName=Ascii.toUpperCase(name);
  }
 else {
    constName=Ascii.toUpperCase(NamingConventions.convertToLowerUnderscore(name));
  }
  if (constName.startsWith("K_")) {
    constName=constName.substring("K_".length());
  }
  return constName;
}
