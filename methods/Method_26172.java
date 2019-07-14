@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!Matchers.equalsMethodDeclaration().matches(tree,state)) {
    return NO_MATCH;
  }
  Set<VarSymbol> impliesNonNull=new HashSet<>();
  Set<VarSymbol> incomingVariableSymbols=new HashSet<>();
  VarSymbol varSymbol=getSymbol(getOnlyElement(tree.getParameters()));
  incomingVariableSymbols.add(varSymbol);
  NullnessAnalysis analysis=NullnessAnalysis.instance(state.context);
  boolean[] crashesWithNull={false};
  new TreePathScanner<Void,Void>(){
    @Override public Void visitMemberSelect(    MemberSelectTree node,    Void unused){
      if (!crashesWithNull[0]) {
        Symbol symbol=getSymbol(node.getExpression());
        if (symbol instanceof VarSymbol && incomingVariableSymbols.contains(symbol)) {
          Nullness nullness=analysis.getNullness(new TreePath(getCurrentPath(),node.getExpression()),state.context);
          if (nullness == Nullness.NULLABLE) {
            crashesWithNull[0]=true;
          }
        }
      }
      return super.visitMemberSelect(node,null);
    }
    @Override public Void visitVariable(    VariableTree variableTree,    Void unused){
      Tree initializer=variableTree.getInitializer();
      VarSymbol symbol=getSymbol(variableTree);
      if ((symbol.flags() & (Flags.FINAL | Flags.EFFECTIVELY_FINAL)) != 0 && initializer instanceof InstanceOfTree) {
        InstanceOfTree instanceOf=(InstanceOfTree)initializer;
        if (instanceOf.getExpression() instanceof IdentifierTree && incomingVariableSymbols.contains(getSymbol(instanceOf.getExpression()))) {
          impliesNonNull.add(getSymbol(variableTree));
        }
      }
      if (incomingVariableSymbols.contains(findVariable(variableTree.getInitializer()))) {
        incomingVariableSymbols.add(getSymbol(variableTree));
      }
      return super.visitVariable(variableTree,null);
    }
    @Override public Void visitIf(    IfTree ifTree,    Void unused){
      ExpressionTree condition=ASTHelpers.stripParentheses(ifTree.getCondition());
      if (condition instanceof IdentifierTree && impliesNonNull.contains(getSymbol(condition))) {
        return scan(ifTree.getElseStatement(),null);
      }
      return super.visitIf(ifTree,unused);
    }
    /** 
 * Unwraps expressions like `(Foo) foo` or `((Foo) foo)` to return the VarSymbol of `foo`, or null if the expression wasn't of this form.
 */
    @Nullable private VarSymbol findVariable(    Tree tree){
      while (tree != null) {
switch (tree.getKind()) {
case TYPE_CAST:
          tree=((TypeCastTree)tree).getExpression();
        break;
case PARENTHESIZED:
      tree=((ParenthesizedTree)tree).getExpression();
    break;
case IDENTIFIER:
  Symbol symbol=getSymbol(tree);
return symbol instanceof VarSymbol ? (VarSymbol)symbol : null;
default :
return null;
}
}
return null;
}
}
.scan(state.getPath(),null);
if (!crashesWithNull[0]) {
return NO_MATCH;
}
String stringAddition=String.format("if (%s == null) { return false; }\n",varSymbol.name);
Fix fix=SuggestedFix.prefixWith(tree.getBody().getStatements().get(0),stringAddition);
return describeMatch(tree,fix);
}
