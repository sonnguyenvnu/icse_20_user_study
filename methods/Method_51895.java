/** 
 * Returns true if this try statement has a   {@code finally} statement,in which case  {@link #getFinally()} won't return {@code null}.
 */
public boolean hasFinally(){
  return getFirstChildOfType(ASTFinallyStatement.class) != null;
}
