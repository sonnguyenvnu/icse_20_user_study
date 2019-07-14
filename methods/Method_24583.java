/** 
 * Sees if buf is of the form [optional whitespace][keyword][optional anything]. It *will* allow keyword to be directly followed by an alphanumeric, _, or &amp;. Will be different if keyword contains regex codes (except *, which is fine).
 */
private boolean lookup_com(final String keyword){
  final String regex="^\\s*" + keyword.replace("*","\\*") + ".*$";
  return Pattern.matches(regex,buf);
}
