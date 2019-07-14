@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  Map<MethodSymbol,MethodDetails> nodes=new HashMap<>();
  new TreePathScanner<Void,Void>(){
    @Override public Void visitClass(    ClassTree classTree,    Void unused){
      if (isSuppressed(classTree)) {
        suppressions++;
        super.visitClass(classTree,null);
        suppressions--;
      }
 else {
        super.visitClass(classTree,null);
      }
      return null;
    }
    @Override public Void visitMethod(    MethodTree tree,    Void unused){
      if (isSuppressed(tree)) {
        suppressions++;
        matchMethod(tree);
        super.visitMethod(tree,null);
        suppressions--;
      }
 else {
        matchMethod(tree);
        super.visitMethod(tree,null);
      }
      return null;
    }
    @Override public Void visitVariable(    VariableTree variableTree,    Void unused){
      if (isSuppressed(variableTree)) {
        suppressions++;
        super.visitVariable(variableTree,null);
        suppressions--;
      }
 else {
        super.visitVariable(variableTree,null);
      }
      return null;
    }
    private void matchMethod(    MethodTree tree){
      MethodSymbol sym=ASTHelpers.getSymbol(tree);
      if (sym.isStatic()) {
        nodes.put(sym,new MethodDetails(tree,true,ImmutableSet.of()));
      }
 else {
        CanBeStaticResult result=CanBeStaticAnalyzer.canBeStaticResult(tree,sym,state);
        boolean isExcluded=isExcluded(tree,state);
        nodes.put(sym,new MethodDetails(tree,result.canPossiblyBeStatic() && !isExcluded && suppressions == 0,result.methodsReferenced()));
      }
    }
  }
.scan(state.getPath(),null);
  propagateNonStaticness(nodes);
  nodes.entrySet().removeIf(entry -> entry.getValue().tree.getModifiers().getFlags().contains(Modifier.STATIC));
  return generateDescription(nodes,state);
}
