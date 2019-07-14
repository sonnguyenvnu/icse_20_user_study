/** 
 * This function is called if the content of a url is bigger than allowed size.
 * @param urlStr - The URL which it's content is bigger than allowed size
 */
protected void onPageBiggerThanMaxSize(String urlStr,long pageSize){
  logger.warn("Skipping a URL: {} which was bigger ( {} ) than max allowed size",urlStr,pageSize);
}
