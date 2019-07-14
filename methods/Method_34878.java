/** 
 * This function is called if the content of a url could not be fetched.
 * @param page Partial page object
 */
protected void onContentFetchError(Page page){
  logger.warn("Can't fetch content of: {}",page.getWebURL().getURL());
}
