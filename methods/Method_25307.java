private static void addRemainingModifiers(Tree tree,VisitorState state,ModifiersTree originalModifiers,Collection<Modifier> toAdd,SuggestedFix.Builder fix){
  if (toAdd.isEmpty()) {
    return;
  }
  int pos=state.getEndPosition(originalModifiers) != Position.NOPOS ? state.getEndPosition(originalModifiers) + 1 : ((JCTree)tree).getStartPosition();
  int insertPos=state.getOffsetTokensForNode(tree).stream().mapToInt(ErrorProneToken::pos).filter(thisPos -> thisPos >= pos).findFirst().orElse(pos);
  fix.replace(insertPos,insertPos,Joiner.on(' ').join(toAdd) + " ");
}
