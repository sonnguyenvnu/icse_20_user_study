@Override public boolean matches(Tree tree,VisitorState state){
  FirstMatchingScanner scanner=new FirstMatchingScanner(state);
  Boolean matchFound=tree.accept(scanner,false);
  return matchFound != null && matchFound;
}
