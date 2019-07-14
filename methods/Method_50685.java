/** 
 * Checks if the metric can be computed on the node. For now, we filter out  {@literal <clinit>, <init> and clone}, which are present in all apex class nodes even if they're not implemented, which may yield unexpected results.
 * @param node The node to check
 * @return True if the metric can be computed
 */
@Override public boolean supports(ASTMethod node){
  return !node.getImage().matches("(<clinit>|<init>|clone)") && !node.getFirstChildOfType(ASTModifierNode.class).isAbstract();
}
