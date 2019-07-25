/** 
 * Sets the fragment of the  {@code pathMapping}.
 */
public EndpointInfoBuilder fragment(String fragment){
  requireNonNull(fragment,"fragment");
  checkState(regexPathPrefix == null,"fragment cannot be set with regexPathPrefix: %s",regexPathPrefix);
  this.fragment=fragment;
  return this;
}
