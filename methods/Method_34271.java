/** 
 * @param grantTypes some grant types
 * @return true if the supplied grant types includes one or more of the redirect types
 */
private boolean containsRedirectGrantType(Set<String> grantTypes){
  for (  String type : grantTypes) {
    if (redirectGrantTypes.contains(type)) {
      return true;
    }
  }
  return false;
}
