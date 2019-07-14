/** 
 * Classes that extends WebCrawler should overwrite this function to tell the crawler whether the given url should be crawled or not. The following default implementation indicates that all urls should be included in the crawl except those with a nofollow flag.
 * @param url the url which we are interested to know whether it should be included in the crawl or not.
 * @param referringPage The Page in which this url was found.
 * @return if the url should be included in the crawl it returns true,otherwise false is returned.
 */
public boolean shouldVisit(Page referringPage,WebURL url){
  if (myController.getConfig().isRespectNoFollow()) {
    return !((referringPage != null && referringPage.getContentType() != null && referringPage.getContentType().contains("html") && ((HtmlParseData)referringPage.getParseData()).getMetaTagValue("robots").contains("nofollow")) || url.getAttribute("rel").contains("nofollow"));
  }
  return true;
}
