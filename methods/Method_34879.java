/** 
 * This function is called if there has been an error in parsing the content.
 * @param webUrl URL which failed on parsing
 */
protected void onParseError(WebURL webUrl,ParseException e) throws ParseException {
  onParseError(webUrl);
}
