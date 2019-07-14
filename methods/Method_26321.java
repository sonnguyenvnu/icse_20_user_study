/** 
 * Validates that  {@code @GuardedBy} strings can be resolved. 
 */
Description validate(Tree tree,VisitorState state){
  GuardedByValidationResult result=GuardedByUtils.isGuardedByValid(tree,state);
  if (result.isValid()) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).setMessage(String.format("Invalid @GuardedBy expression: %s",result.message())).build();
}
