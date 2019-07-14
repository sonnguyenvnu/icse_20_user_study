@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  MethodSymbol constructor=ASTHelpers.getSymbol(tree);
  if (constructor == null) {
    return NO_MATCH;
  }
  Symbol owner=constructor.owner;
  Description description=describeIfObsolete(tree.getClassBody() == null ? tree.getIdentifier() : null,owner.name.isEmpty() ? state.getTypes().directSupertypes(owner.asType()) : ImmutableList.of(owner.asType()),state);
  if (description == NO_MATCH) {
    return NO_MATCH;
  }
  if (owner.getQualifiedName().contentEquals("java.lang.StringBuffer")) {
    boolean[] found={false};
    new TreeScanner<Void,Void>(){
      @Override public Void visitMethodInvocation(      MethodInvocationTree tree,      Void unused){
        if (MATCHER_STRINGBUFFER.matches(tree,state)) {
          found[0]=true;
        }
        return null;
      }
    }
.scan(state.getPath().getCompilationUnit(),null);
    if (found[0]) {
      return NO_MATCH;
    }
  }
  return description;
}
