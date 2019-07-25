public static String decapitalize(String s){
  if (s == null || s.isEmpty() || Character.isLowerCase(s.charAt(0))) {
    return s;
  }
  StringBuilder result=new StringBuilder(s);
  for (int i=0; i < result.length() && Character.isUpperCase(result.charAt(i)); i++) {
    if (i == 0 || i == result.length() - 1 || Character.isUpperCase(result.charAt(i + 1))) {
      result.setCharAt(i,Character.toLowerCase(result.charAt(i)));
    }
  }
  return result.toString();
}
