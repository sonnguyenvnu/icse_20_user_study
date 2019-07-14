/** 
 * Check if host matches the registered value.
 * @param registered the registered host
 * @param requested the requested host
 * @return true if they match
 */
protected boolean hostMatches(String registered,String requested){
  if (matchSubdomains) {
    return registered.equals(requested) || requested.endsWith("." + registered);
  }
  return registered.equals(requested);
}
