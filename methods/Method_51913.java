/** 
 * Returns true if this nodes declares an exception parameter in a  {@code catch} statement.
 */
public boolean isExceptionBlockParameter(){
  return jjtGetParent().jjtGetParent() instanceof ASTCatchStatement;
}
