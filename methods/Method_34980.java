/** 
 * Returns a copy of the input string in which all  {@linkplain #isUpperCase(char) uppercase ASCIIcharacters} have been converted to lowercase. All other characters are copied withoutmodification.
 */
@Nonnull public static String toLowerCase(@Nonnull String string){
  int length=string.length();
  for (int i=0; i < length; i++) {
    if (isUpperCase(string.charAt(i))) {
      char[] chars=string.toCharArray();
      for (; i < length; i++) {
        char c=chars[i];
        if (isUpperCase(c)) {
          chars[i]=(char)(c ^ 0x20);
        }
      }
      return String.valueOf(chars);
    }
  }
  return string;
}
