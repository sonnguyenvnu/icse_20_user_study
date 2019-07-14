/** 
 * Are the two String values the same. The Strings can be optionally trimmed before checking. The Strings can be optionally compared ignoring case. The Strings can be have embedded whitespace standardized before comparing. Two null values are treated as equal.
 * @param s1                    The first String.
 * @param s2                    The second String.
 * @param trim                  Indicates if the Strings should be trimmed before comparison.
 * @param ignoreCase            Indicates if the case of the Strings should ignored during comparison.
 * @param standardizeWhitespace Indicates if the embedded whitespace should be standardized before comparison.
 * @return <code>true</code> if the Strings are the same, <code>false</code> otherwise.
 */
public static boolean isSame(String s1,String s2,boolean trim,boolean ignoreCase,boolean standardizeWhitespace){
  if (s1 == null && s2 == null) {
    return true;
  }
 else   if (s1 == null || s2 == null) {
    return false;
  }
 else {
    if (trim) {
      s1=s1.trim();
      s2=s2.trim();
    }
    if (standardizeWhitespace) {
      s1=s1.replaceAll("\\s+"," ");
      s2=s2.replaceAll("\\s+"," ");
    }
    return ignoreCase ? s1.equalsIgnoreCase(s2) : s1.equals(s2);
  }
}
