/** 
 * Emitted when the crawler is redirected to an invalid Location.
 * @param page
 */
protected void onRedirectedToInvalidUrl(Page page){
  logger.warn("Unexpected error, URL: {} is redirected to NOTHING",page.url.getURL());
}
