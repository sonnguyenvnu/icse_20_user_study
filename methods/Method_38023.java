/** 
 * Finds common prefix for several strings. Returns an empty string if arguments do not have a common prefix.
 */
public static String findCommonPrefix(final String... strings){
  StringBuilder prefix=new StringBuilder();
  int index=0;
  char c=0;
  loop:   while (true) {
    for (int i=0; i < strings.length; i++) {
      String s=strings[i];
      if (index == s.length()) {
        break loop;
      }
      if (i == 0) {
        c=s.charAt(index);
      }
 else {
        if (s.charAt(index) != c) {
          break loop;
        }
      }
    }
    index++;
    prefix.append(c);
  }
  return prefix.length() == 0 ? StringPool.EMPTY : prefix.toString();
}
