private Description suggestReplacement(ExpressionTree tree,BigDecimal actualValue,String replacement){
  return buildDescription(tree).setMessage(message() + String.format(ACTUAL_VALUE,actualValue)).addFix(SuggestedFix.replace(tree,replacement)).build();
}
