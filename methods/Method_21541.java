private static String getParamFromHint(String hint,String prefix){
  if (!hint.contains("("))   return null;
  return hint.replace(prefix,"").replaceAll("\\s*\\(\\s*","").replaceAll("\\s*\\,\\s*",",").replaceAll("\\s*\\)\\s*","");
}
