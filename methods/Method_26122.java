/** 
 * Returns true if  {@code collection} is modified by an enclosing loop. 
 */
static boolean enclosingLoop(TreePath path,ExpressionTree collection){
  for (  Tree node : path) {
switch (node.getKind()) {
case METHOD:
case CLASS:
case LAMBDA_EXPRESSION:
      return false;
case ENHANCED_FOR_LOOP:
    if (sameVariable(collection,((EnhancedForLoopTree)node).getExpression())) {
      return true;
    }
  break;
default :
}
}
return false;
}
