private Description checkDeclarations(List<? extends Tree> children,VisitorState state){
  PeekingIterator<Tree> it=Iterators.<Tree>peekingIterator(children.iterator());
  while (it.hasNext()) {
    if (it.peek().getKind() != Tree.Kind.VARIABLE) {
      it.next();
      continue;
    }
    VariableTree variableTree=(VariableTree)it.next();
    ArrayList<JCVariableDecl> fragments=new ArrayList<>();
    fragments.add((JCVariableDecl)variableTree);
    while (it.hasNext() && it.peek().getKind() == Tree.Kind.VARIABLE && ((JCTree)variableTree).getStartPosition() == ((JCTree)it.peek()).getStartPosition()) {
      fragments.add((JCVariableDecl)it.next());
    }
    if (fragments.size() == 1) {
      continue;
    }
    Fix fix=SuggestedFix.replace(fragments.get(0).getStartPosition(),state.getEndPosition(Iterables.getLast(fragments)),fragments.stream().map(this::pretty).collect(joining("")));
    state.reportMatch(describeMatch(fragments.get(0),fix));
  }
  return NO_MATCH;
}
