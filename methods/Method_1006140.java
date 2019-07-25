/** 
 * @param toPurify
 * @param warn may-be-null
 * @return
 */
public static String purify(String toPurify,Warn warn){
  StringBuilder sb=new StringBuilder();
  char[] cs=toPurify.toCharArray();
  int n=cs.length;
  int i=0;
  int braceLevel=0;
  while (i < n) {
    char c=cs[i];
    if (Character.isWhitespace(c) || (c == '-') || (c == '~')) {
      sb.append(' ');
    }
 else     if (Character.isLetterOrDigit(c)) {
      sb.append(c);
    }
 else     if (c == '{') {
      braceLevel++;
      if ((braceLevel == 1) && ((i + 1) < n) && (cs[i + 1] == '\\')) {
        i++;
        while ((i < n) && (braceLevel > 0)) {
          i++;
          BibtexCaseChanger.findSpecialChar(cs,i).ifPresent(sb::append);
          while ((i < n) && Character.isLetter(cs[i])) {
            i++;
          }
          while ((i < n) && (braceLevel > 0) && ((c=cs[i]) != '\\')) {
            if (Character.isLetterOrDigit(c)) {
              sb.append(c);
            }
 else             if (c == '}') {
              braceLevel--;
            }
 else             if (c == '{') {
              braceLevel++;
            }
            i++;
          }
        }
        continue;
      }
    }
 else     if (c == '}') {
      if (braceLevel > 0) {
        braceLevel--;
      }
 else {
        if (warn != null) {
          warn.warn("Unbalanced brace in string for purify$: " + toPurify);
        }
      }
    }
    i++;
  }
  if ((braceLevel != 0) && (warn != null)) {
    warn.warn("Unbalanced brace in string for purify$: " + toPurify);
  }
  return sb.toString();
}
