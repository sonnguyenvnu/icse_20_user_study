/** 
 * Creates an appropriate URL resolver scanner for this url protocol.
 * @param protocol The protocol of the location url to scan.
 * @return The url resolver for this protocol.
 */
private UrlResolver createUrlResolver(String protocol){
  if (new FeatureDetector(classLoader).isJBossVFSv2Available() && protocol.startsWith("vfs")) {
    return new JBossVFSv2UrlResolver();
  }
  return new DefaultUrlResolver();
}
