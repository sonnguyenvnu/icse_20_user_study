private Description maybeFix(Tree tree,VisitorState state,Type matchedType,Optional<Fix> fix){
  Description.Builder description=buildDescription(tree);
  fix.ifPresent(description::addFix);
  descriptionMessageForDefaultMatch(matchedType,state).ifPresent(description::setMessage);
  return description.build();
}
