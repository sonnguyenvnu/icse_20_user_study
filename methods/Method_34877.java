/** 
 * This function is called if the content of a url could not be fetched.
 * @param webUrl URL which content failed to be fetched
 * @deprecated use {@link #onContentFetchError(Page)}
 */
@Deprecated protected void onContentFetchError(WebURL webUrl){
  logger.warn("Can't fetch content of: {}",webUrl.getURL());
}
