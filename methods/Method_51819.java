/** 
 * Returns true if this enum constant defines a body, which is compiled like an anonymous class. If this method returns false, then  {@link #getQualifiedName()}returns  {@code null}.
 */
public boolean isAnonymousClass(){
  return getFirstChildOfType(ASTClassOrInterfaceBody.class) != null;
}
