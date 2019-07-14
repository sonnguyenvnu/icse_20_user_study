/** 
 * Exclude the pattern value Expression: "{value}", subclass could override this method.
 * @param valueExpression
 * @return
 */
protected boolean isExcludedValue(String valueExpression){
  return StringUtils.hasText(valueExpression) && valueExpression.startsWith("{") && valueExpression.endsWith("}");
}
