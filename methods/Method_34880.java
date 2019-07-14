/** 
 * This function is called if there has been an error in parsing the content.
 * @param webUrl URL which failed on parsing
 */
@Deprecated protected void onParseError(WebURL webUrl){
  logger.warn("Parsing error of: {}",webUrl.getURL());
}
