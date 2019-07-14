/** 
 * Removes all substring occurrences from the string.
 * @param s      source string
 * @param sub    substring to remove
 */
public static String remove(final String s,final String sub){
  int c=0;
  int sublen=sub.length();
  if (sublen == 0) {
    return s;
  }
  int i=s.indexOf(sub,c);
  if (i == -1) {
    return s;
  }
  StringBuilder sb=new StringBuilder(s.length());
  do {
    sb.append(s,c,i);
    c=i + sublen;
  }
 while ((i=s.indexOf(sub,c)) != -1);
  if (c < s.length()) {
    sb.append(s,c,s.length());
  }
  return sb.toString();
}
