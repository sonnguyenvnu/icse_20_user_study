/** 
 * Returns true if this expression defines a body, which is compiled to an anonymous class. If this method returns false, then  {@link #getQualifiedName()}returns  {@code null}.
 */
public boolean isAnonymousClass(){
  if (jjtGetNumChildren() > 1) {
    return jjtGetChild(jjtGetNumChildren() - 1) instanceof ASTClassOrInterfaceBody;
  }
  return false;
}
