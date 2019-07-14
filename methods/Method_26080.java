@Override public Description matchSwitch(SwitchTree tree,VisitorState state){
  Type switchType=ASTHelpers.getType(tree.getExpression());
  if (switchType.asElement().getKind() == ElementKind.ENUM) {
    return NO_MATCH;
  }
  Optional<? extends CaseTree> maybeDefault=tree.getCases().stream().filter(c -> c.getExpression() == null).findFirst();
  if (!maybeDefault.isPresent()) {
    Description.Builder description=buildDescription(tree);
    if (!tree.getCases().isEmpty()) {
      CaseTree lastCase=getLast(tree.getCases());
      String replacement;
      if (lastCase.getStatements().isEmpty() || Reachability.canCompleteNormally(Iterables.getLast(lastCase.getStatements()))) {
        replacement="\nbreak;\ndefault: // fall out\n";
      }
 else {
        replacement="\ndefault: // fall out\n";
      }
      description.addFix(SuggestedFix.postfixWith(getLast(tree.getCases()),replacement));
    }
    return description.build();
  }
  CaseTree defaultCase=maybeDefault.get();
  if (!defaultCase.getStatements().isEmpty()) {
    return NO_MATCH;
  }
  int idx=tree.getCases().indexOf(defaultCase);
  if (idx != tree.getCases().size() - 1) {
    return NO_MATCH;
  }
  if (state.getOffsetTokens(state.getEndPosition(defaultCase),state.getEndPosition(tree)).stream().anyMatch(t -> !t.comments().isEmpty())) {
    return NO_MATCH;
  }
  return buildDescription(defaultCase).setMessage("Default case should be documented with a comment").addFix(SuggestedFix.postfixWith(defaultCase," // fall out")).build();
}
