public Optional<Fix> buildFix(MethodInvocationTree tree,VisitorState state){
  Tree arg=getOnlyElement(tree.getArguments());
  String methodAndArgument=getMethodAndArgument(arg,state);
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (parent instanceof EnhancedForLoopTree && ((EnhancedForLoopTree)parent).getExpression().equals(tree)) {
    return Optional.of(replaceWithSplitter(SuggestedFix.builder(),tree,methodAndArgument,state,"split",false).build());
  }
  if (parent instanceof ArrayAccessTree) {
    ArrayAccessTree arrayAccessTree=(ArrayAccessTree)parent;
    if (!arrayAccessTree.getExpression().equals(tree)) {
      return Optional.empty();
    }
    SuggestedFix.Builder fix=SuggestedFix.builder().addImport("com.google.common.collect.Iterables").replace(((JCTree)arrayAccessTree).getStartPosition(),((JCTree)arrayAccessTree).getStartPosition(),"Iterables.get(").replace(state.getEndPosition(arrayAccessTree.getExpression()),((JCTree)arrayAccessTree.getIndex()).getStartPosition(),String.format(", ")).replace(state.getEndPosition(arrayAccessTree.getIndex()),state.getEndPosition(arrayAccessTree),")");
    return Optional.of(replaceWithSplitter(fix,tree,methodAndArgument,state,"split",false).build());
  }
  if (!(parent instanceof VariableTree)) {
    return Optional.empty();
  }
  VariableTree varTree=(VariableTree)parent;
  if (!varTree.getInitializer().equals(tree)) {
    return Optional.empty();
  }
  VarSymbol sym=ASTHelpers.getSymbol(varTree);
  TreePath enclosing=findEnclosing(state);
  if (enclosing == null) {
    return Optional.empty();
  }
  List<TreePath> uses=new ArrayList<>();
  new TreePathScanner<Void,Void>(){
    @Override public Void visitIdentifier(    IdentifierTree tree,    Void unused){
      if (Objects.equals(sym,ASTHelpers.getSymbol(tree))) {
        uses.add(getCurrentPath());
      }
      return super.visitIdentifier(tree,null);
    }
  }
.scan(enclosing,null);
  SuggestedFix.Builder fix=SuggestedFix.builder();
  boolean[] needsList={false};
  boolean[] needsMutableList={false};
  for (  TreePath path : uses) {
class UseFixer extends TreePathScanner<Boolean,Void> {
      @Override public Boolean visitEnhancedForLoop(      EnhancedForLoopTree tree,      Void unused){
        return sym.equals(ASTHelpers.getSymbol(tree.getExpression()));
      }
      @Override public Boolean visitArrayAccess(      ArrayAccessTree tree,      Void unused){
        ExpressionTree expression=tree.getExpression();
        ExpressionTree index=tree.getIndex();
        if (!sym.equals(ASTHelpers.getSymbol(expression))) {
          return false;
        }
        Tree parent=getCurrentPath().getParentPath().getLeaf();
        if (parent instanceof AssignmentTree && ((AssignmentTree)parent).getVariable() == tree) {
          AssignmentTree assignmentTree=(AssignmentTree)parent;
          fix.replace(state.getEndPosition(expression),((JCTree)index).getStartPosition(),".set(").replace(state.getEndPosition(index),((JCTree)assignmentTree.getExpression()).getStartPosition(),", ").postfixWith(assignmentTree,")");
          needsMutableList[0]=true;
        }
 else {
          fix.replace(state.getEndPosition(expression),((JCTree)index).getStartPosition(),".get(").replace(state.getEndPosition(index),state.getEndPosition(tree),")");
        }
        needsList[0]=true;
        return true;
      }
      @Override public Boolean visitMemberSelect(      MemberSelectTree tree,      Void aVoid){
        if (sym.equals(ASTHelpers.getSymbol(tree.getExpression())) && tree.getIdentifier().contentEquals("length")) {
          fix.replace(state.getEndPosition(tree.getExpression()),state.getEndPosition(tree),".size()");
          needsList[0]=true;
          return true;
        }
        return false;
      }
    }
    if (!firstNonNull(new UseFixer().scan(path.getParentPath(),null),false)) {
      return Optional.empty();
    }
  }
  Tree varType=varTree.getType();
  boolean isImplicitlyTyped=((JCTree)varType).getStartPosition() < 0;
  if (needsList[0]) {
    if (!isImplicitlyTyped) {
      fix.replace(varType,"List<String>").addImport("java.util.List");
    }
    replaceWithSplitter(fix,tree,methodAndArgument,state,"splitToList",needsMutableList[0]);
  }
 else {
    if (!isImplicitlyTyped) {
      fix.replace(varType,"Iterable<String>");
    }
    replaceWithSplitter(fix,tree,methodAndArgument,state,"split",needsMutableList[0]);
  }
  return Optional.of(fix.build());
}
