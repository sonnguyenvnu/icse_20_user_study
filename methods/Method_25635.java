private static boolean treesMatch(ExpressionTree lhs1,ExpressionTree rhs1,ExpressionTree lhs2,ExpressionTree rhs2){
  return (ASTHelpers.sameVariable(lhs1,lhs2) && ASTHelpers.sameVariable(rhs1,rhs2)) || (ASTHelpers.sameVariable(lhs1,rhs2) && ASTHelpers.sameVariable(rhs1,lhs2));
}
