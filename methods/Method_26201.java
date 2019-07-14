/** 
 * Matches if the expression is a numeric 0, or either of the strings "none" or "0px". These are the values which will cause the outline to be invisible and therefore impede accessibility.
 */
private static boolean constantNoneOrZero(ExpressionTree arg){
  Object value=constValue(arg);
  if (value instanceof String && NONE_STRINGS.contains(value)) {
    return true;
  }
  return value instanceof Number && ((Number)value).doubleValue() == 0.0;
}
