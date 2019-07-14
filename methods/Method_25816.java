@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (tree.getBody() == null) {
    return Description.NO_MATCH;
  }
  ClassTree declaringClass=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  if (!COMPARABLE_CLASS_MATCHER.matches(declaringClass,state) && !COMPARATOR_CLASS_MATCHER.matches(declaringClass,state)) {
    return Description.NO_MATCH;
  }
  if (!COMPARABLE_METHOD_MATCHER.matches(tree,state) && !COMPARATOR_METHOD_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  final Set<ComparisonResult> seenResults=EnumSet.noneOf(ComparisonResult.class);
  final TreeVisitor<Void,VisitorState> visitReturnExpression=new SimpleTreeVisitor<Void,VisitorState>(){
    @Override protected Void defaultAction(    Tree node,    VisitorState state){
      seenResults.add(node.accept(CONSTANT_VISITOR,state));
      return null;
    }
    @Override public Void visitConditionalExpression(    ConditionalExpressionTree node,    VisitorState state){
      node.getTrueExpression().accept(this,state);
      node.getFalseExpression().accept(this,state);
      return null;
    }
  }
;
  tree.getBody().accept(new TreeScanner<Void,VisitorState>(){
    @Override public Void visitReturn(    ReturnTree node,    VisitorState state){
      return node.getExpression().accept(visitReturnExpression,state);
    }
  }
,state);
  if (seenResults.isEmpty() || seenResults.contains(ComparisonResult.NONCONSTANT)) {
    return Description.NO_MATCH;
  }
  if (!seenResults.contains(ComparisonResult.ZERO)) {
    if (tree.getBody().getStatements().size() == 1 && tree.getBody().getStatements().get(0).getKind() == Kind.RETURN) {
      ReturnTree returnTree=(ReturnTree)tree.getBody().getStatements().get(0);
      if (returnTree.getExpression().getKind() == Kind.CONDITIONAL_EXPRESSION) {
        ConditionalExpressionTree condTree=(ConditionalExpressionTree)returnTree.getExpression();
        ExpressionTree conditionExpr=condTree.getCondition();
        conditionExpr=ASTHelpers.stripParentheses(conditionExpr);
        if (!(conditionExpr instanceof BinaryTree)) {
          return describeMatch(tree);
        }
        ComparisonResult trueConst=condTree.getTrueExpression().accept(CONSTANT_VISITOR,state);
        ComparisonResult falseConst=condTree.getFalseExpression().accept(CONSTANT_VISITOR,state);
        boolean trueFirst;
        if (trueConst == ComparisonResult.NEGATIVE_CONSTANT && falseConst == ComparisonResult.POSITIVE_CONSTANT) {
          trueFirst=true;
        }
 else         if (trueConst == ComparisonResult.POSITIVE_CONSTANT && falseConst == ComparisonResult.NEGATIVE_CONSTANT) {
          trueFirst=false;
        }
 else {
          return describeMatch(tree);
        }
switch (conditionExpr.getKind()) {
case LESS_THAN:
case LESS_THAN_EQUAL:
          break;
case GREATER_THAN:
case GREATER_THAN_EQUAL:
        trueFirst=!trueFirst;
      break;
default :
    return describeMatch(tree);
}
BinaryTree binaryExpr=(BinaryTree)conditionExpr;
Type ty=ASTHelpers.getType(binaryExpr.getLeftOperand());
Types types=state.getTypes();
Symtab symtab=state.getSymtab();
ExpressionTree first=trueFirst ? binaryExpr.getLeftOperand() : binaryExpr.getRightOperand();
ExpressionTree second=trueFirst ? binaryExpr.getRightOperand() : binaryExpr.getLeftOperand();
String compareType;
if (types.isSameType(ty,symtab.intType)) {
  compareType="Integer";
}
 else if (types.isSameType(ty,symtab.longType)) {
  compareType="Long";
}
 else {
  return describeMatch(tree);
}
return describeMatch(condTree,SuggestedFix.replace(condTree,String.format("%s.compare(%s, %s)",compareType,state.getSourceForNode(first),state.getSourceForNode(second))));
}
}
return describeMatch(tree);
}
if (COMPARATOR_METHOD_MATCHER.matches(tree,state) && (seenResults.contains(ComparisonResult.NEGATIVE_CONSTANT) != seenResults.contains(ComparisonResult.POSITIVE_CONSTANT))) {
return describeMatch(tree);
}
 else {
return Description.NO_MATCH;
}
}
