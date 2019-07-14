/** 
 * Whether the signature has been validated.
 * @return Whether the signature has been validated.
 */
@Override public boolean isAuthenticated(){
  return isSignatureValidated();
}
