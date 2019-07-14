/** 
 * Get the verification key for the token signatures. The principal has to be provided only if the key is secret (shared not public).
 * @param principal the currently authenticated user if there is one
 * @return the key used to verify tokens
 */
@RequestMapping(value="/oauth/token_key",method=RequestMethod.GET) @ResponseBody public Map<String,String> getKey(Principal principal){
  if ((principal == null || principal instanceof AnonymousAuthenticationToken) && !converter.isPublic()) {
    throw new AccessDeniedException("You need to authenticate to see a shared key");
  }
  Map<String,String> result=converter.getKey();
  return result;
}
