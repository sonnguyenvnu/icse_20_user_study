/** 
 * Replaces all occurrences of a certain pattern in a string with a replacement string. This is the fastest replace function known to author.
 * @param s      string to be inspected
 * @param sub    string pattern to be replaced
 * @param with   string that should go where the pattern was
 */
public static String replace(final String s,final String sub,final String with){
  if (sub.isEmpty()) {
    return s;
  }
  int c=0;
  int i=s.indexOf(sub,c);
  if (i == -1) {
    return s;
  }
  int length=s.length();
  StringBuilder sb=new StringBuilder(length + with.length());
  do {
    sb.append(s,c,i);
    sb.append(with);
    c=i + sub.length();
  }
 while ((i=s.indexOf(sub,c)) != -1);
  if (c < length) {
    sb.append(s,c,length);
  }
  return sb.toString();
}
