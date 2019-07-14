/** 
 * Returns <code>true</code> if resource link has to be downloaded. By default, if resource link starts with "http://" or with "https://" it will be considered as external resource.
 */
protected boolean isExternalResource(final String link){
  return link.startsWith("http://") || (link.startsWith("https://"));
}
