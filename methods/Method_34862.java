/** 
 * Validates the configs specified by this instance.
 * @throws Exception on Validation fail
 */
public void validate() throws Exception {
  if (crawlStorageFolder == null) {
    throw new Exception("Crawl storage folder is not set in the CrawlConfig.");
  }
  if (politenessDelay < 0) {
    throw new Exception("Invalid value for politeness delay: " + politenessDelay);
  }
  if (maxDepthOfCrawling < -1) {
    throw new Exception("Maximum crawl depth should be either a positive number or -1 for unlimited depth" + ".");
  }
  if (maxDepthOfCrawling > Short.MAX_VALUE) {
    throw new Exception("Maximum value for crawl depth is " + Short.MAX_VALUE);
  }
}
