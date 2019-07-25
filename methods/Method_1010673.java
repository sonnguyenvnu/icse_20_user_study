public static String capitalize(String s){
  if (s == null || s.isEmpty())   return s;
  char[] chars=s.toCharArray();
  chars[0]=Character.toTitleCase(chars[0]);
  return new String(chars);
}
