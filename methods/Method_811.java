public static String decapitalize(String name){
  if (name == null || name.length() == 0) {
    return name;
  }
  if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
    return name;
  }
  char chars[]=name.toCharArray();
  chars[0]=Character.toLowerCase(chars[0]);
  return new String(chars);
}
