/** 
 * Matches patterns containing YYYY but not ww, signifying that it was not intended to be a week date. If the pattern is a string literal, suggest replacing the YYYY with yyyy. <p>Given the  {@link ExpressionTree} representing the pattern argument to the various methods inSimpleDateFormat that accept a pattern, construct the description for this matcher to return. May be  {@link Description#NO_MATCH} if the pattern does not have a constant value, does not usethe week year format specifier, or is in proper week date format.
 */
private Description constructDescription(Tree tree,ExpressionTree patternArg,VisitorState state){
  String pattern=(String)ASTHelpers.constValue(patternArg);
  if (pattern != null && pattern.contains("Y") && !pattern.contains("w")) {
    Description.Builder description=buildDescription(tree);
    getFix(patternArg,state).ifPresent(description::addFix);
    return description.build();
  }
  return Description.NO_MATCH;
}
