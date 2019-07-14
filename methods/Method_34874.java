/** 
 * This function is called before processing of the page's URL It can be overridden by subclasses for tweaking of the url before processing it. For example, http://abc.com/def?a=123 - http://abc.com/def
 * @param curURL current URL which can be tweaked before processing
 * @return tweaked WebURL
 */
protected WebURL handleUrlBeforeProcess(WebURL curURL){
  return curURL;
}
