/** 
 * Checks whether a specific text matches the defined grok expression.
 * @param text the string to match
 * @return true if grok expression matches text, false otherwise.
 */
public boolean match(String text){
  Matcher matcher=compiledExpression.matcher(text.getBytes(StandardCharsets.UTF_8));
  int result=matcher.search(0,text.length(),Option.DEFAULT);
  return (result != -1);
}
