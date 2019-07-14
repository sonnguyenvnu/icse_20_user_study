/** 
 * Adds a new seed URL. A seed URL is a URL that is fetched by the crawler to extract new URLs in it and follow them for crawling. You can also specify a specific document id to be assigned to this seed URL. This document id needs to be unique. Also, note that if you add three seeds with document ids 1,2, and 7. Then the next URL that is found during the crawl will get a doc id of 8. Also you need to ensure to add seeds in increasing order of document ids. Specifying doc ids is mainly useful when you have had a previous crawl and have stored the results and want to start a new crawl with seeds which get the same document ids as the previous crawl.
 * @param pageUrl the URL of the seed
 * @param docId the document id that you want to be assigned to this seed URL.
 * @throws InterruptedException
 * @throws IOException
 */
public void addSeed(String pageUrl,int docId) throws IOException, InterruptedException {
  String canonicalUrl=URLCanonicalizer.getCanonicalURL(pageUrl);
  if (canonicalUrl == null) {
    logger.error("Invalid seed URL: {}",pageUrl);
  }
 else {
    if (docId < 0) {
      docId=docIdServer.getDocId(canonicalUrl);
      if (docId > 0) {
        logger.trace("This URL is already seen.");
        return;
      }
      docId=docIdServer.getNewDocID(canonicalUrl);
    }
 else {
      try {
        docIdServer.addUrlAndDocId(canonicalUrl,docId);
      }
 catch (      RuntimeException e) {
        if (config.isHaltOnError()) {
          throw e;
        }
 else {
          logger.error("Could not add seed: {}",e.getMessage());
        }
      }
    }
    WebURL webUrl=new WebURL();
    webUrl.setTldList(tldList);
    webUrl.setURL(canonicalUrl);
    webUrl.setDocid(docId);
    webUrl.setDepth((short)0);
    if (robotstxtServer.allows(webUrl)) {
      frontier.schedule(webUrl);
    }
 else {
      logger.warn("Robots.txt does not allow this seed: {}",pageUrl);
    }
  }
}
