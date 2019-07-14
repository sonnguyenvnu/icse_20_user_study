/** 
 * Simply return if the image start with keyword 'this' or 'super'.
 * @return true, if keyword is used, false otherwise.
 */
public boolean useThisOrSuper(){
  Node node=location.jjtGetParent();
  if (node instanceof ASTPrimaryExpression) {
    ASTPrimaryExpression primaryExpression=(ASTPrimaryExpression)node;
    ASTPrimaryPrefix prefix=(ASTPrimaryPrefix)primaryExpression.jjtGetChild(0);
    if (prefix != null) {
      return prefix.usesSuperModifier() || prefix.usesThisModifier();
    }
  }
  return image.startsWith(THIS_DOT) || image.startsWith(SUPER_DOT);
}
