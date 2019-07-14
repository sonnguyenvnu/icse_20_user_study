private static String capitalize(String s){
  if (s == null)   return null;
  char[] chars=s.toCharArray();
  boolean prevIsSpace=true;
  for (int i=0; i < chars.length; i++) {
    if (!prevIsSpace && Character.isLetter(chars[i])) {
      chars[i]=Character.toLowerCase(chars[i]);
    }
 else {
      prevIsSpace=chars[i] == ' ';
    }
  }
  return new String(chars);
}
