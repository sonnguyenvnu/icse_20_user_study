/** 
 * Flag to determine the behaviour on access denied. If set then we throw an  {@link InsufficientScopeException}instead of returning  {@link AccessDecisionVoter#ACCESS_DENIED}. This is unconventional for an access decision voter because it vetos the other voters in the chain, but it enables us to pass a message to the caller with information about the required scope.
 * @param throwException the flag to set (default true)
 */
public void setThrowException(boolean throwException){
  this.throwException=throwException;
}
