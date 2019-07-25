/** 
 * Negates  {@code conditionTemplate}, removing unnecessary brackets and double-negatives if possible.
 */
private static String negate(String conditionTemplate){
  if (conditionTemplate.startsWith("!") && !BOOLEAN_BINARY_OPERATOR.matcher(conditionTemplate).find()) {
    return conditionTemplate.substring(1);
  }
 else   if (ANY_OPERATOR.matcher(conditionTemplate).find()) {
    return "!(" + conditionTemplate + ")";
  }
 else {
    return "!" + conditionTemplate;
  }
}
