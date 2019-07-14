private void processPage(WebURL curURL) throws IOException, InterruptedException, ParseException {
  PageFetchResult fetchResult=null;
  Page page=new Page(curURL);
  try {
    if (curURL == null) {
      return;
    }
    fetchResult=pageFetcher.fetchPage(curURL);
    int statusCode=fetchResult.getStatusCode();
    handlePageStatusCode(curURL,statusCode,EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode,Locale.ENGLISH));
    page.setFetchResponseHeaders(fetchResult.getResponseHeaders());
    page.setStatusCode(statusCode);
    if (statusCode < 200 || statusCode > 299) {
      if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MULTIPLE_CHOICES || statusCode == HttpStatus.SC_SEE_OTHER || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT || statusCode == 308) {
        page.setRedirect(true);
        String movedToUrl=fetchResult.getMovedToUrl();
        if (movedToUrl == null) {
          onRedirectedToInvalidUrl(page);
          return;
        }
        page.setRedirectedToUrl(movedToUrl);
        onRedirectedStatusCode(page);
        if (myController.getConfig().isFollowRedirects()) {
          int newDocId=docIdServer.getDocId(movedToUrl);
          if (newDocId > 0) {
            logger.debug("Redirect page: {} is already seen",curURL);
            return;
          }
          WebURL webURL=new WebURL();
          webURL.setTldList(myController.getTldList());
          webURL.setURL(movedToUrl);
          webURL.setParentDocid(curURL.getParentDocid());
          webURL.setParentUrl(curURL.getParentUrl());
          webURL.setDepth(curURL.getDepth());
          webURL.setDocid(-1);
          webURL.setAnchor(curURL.getAnchor());
          if (shouldVisit(page,webURL)) {
            if (!shouldFollowLinksIn(webURL) || robotstxtServer.allows(webURL)) {
              webURL.setDocid(docIdServer.getNewDocID(movedToUrl));
              frontier.schedule(webURL);
            }
 else {
              logger.debug("Not visiting: {} as per the server's \"robots.txt\" policy",webURL.getURL());
            }
          }
 else {
            logger.debug("Not visiting: {} as per your \"shouldVisit\" policy",webURL.getURL());
          }
        }
      }
 else {
        String description=EnglishReasonPhraseCatalog.INSTANCE.getReason(fetchResult.getStatusCode(),Locale.ENGLISH);
        String contentType=fetchResult.getEntity() == null ? "" : fetchResult.getEntity().getContentType() == null ? "" : fetchResult.getEntity().getContentType().getValue();
        onUnexpectedStatusCode(curURL.getURL(),fetchResult.getStatusCode(),contentType,description);
      }
    }
 else {
      if (!curURL.getURL().equals(fetchResult.getFetchedUrl())) {
        if (docIdServer.isSeenBefore(fetchResult.getFetchedUrl())) {
          logger.debug("Redirect page: {} has already been seen",curURL);
          return;
        }
        curURL.setURL(fetchResult.getFetchedUrl());
        curURL.setDocid(docIdServer.getNewDocID(fetchResult.getFetchedUrl()));
      }
      if (!fetchResult.fetchContent(page,myController.getConfig().getMaxDownloadSize())) {
        throw new ContentFetchException();
      }
      if (page.isTruncated()) {
        logger.warn("Warning: unknown page size exceeded max-download-size, truncated to: " + "({}), at URL: {}",myController.getConfig().getMaxDownloadSize(),curURL.getURL());
      }
      parser.parse(page,curURL.getURL());
      if (shouldFollowLinksIn(page.getWebURL())) {
        ParseData parseData=page.getParseData();
        List<WebURL> toSchedule=new ArrayList<>();
        int maxCrawlDepth=myController.getConfig().getMaxDepthOfCrawling();
        for (        WebURL webURL : parseData.getOutgoingUrls()) {
          webURL.setParentDocid(curURL.getDocid());
          webURL.setParentUrl(curURL.getURL());
          int newdocid=docIdServer.getDocId(webURL.getURL());
          if (newdocid > 0) {
            webURL.setDepth((short)-1);
            webURL.setDocid(newdocid);
          }
 else {
            webURL.setDocid(-1);
            webURL.setDepth((short)(curURL.getDepth() + 1));
            if ((maxCrawlDepth == -1) || (curURL.getDepth() < maxCrawlDepth)) {
              if (shouldVisit(page,webURL)) {
                if (robotstxtServer.allows(webURL)) {
                  webURL.setDocid(docIdServer.getNewDocID(webURL.getURL()));
                  toSchedule.add(webURL);
                }
 else {
                  logger.debug("Not visiting: {} as per the server's \"robots.txt\" " + "policy",webURL.getURL());
                }
              }
 else {
                logger.debug("Not visiting: {} as per your \"shouldVisit\" policy",webURL.getURL());
              }
            }
          }
        }
        frontier.scheduleAll(toSchedule);
      }
 else {
        logger.debug("Not looking for links in page {}, " + "as per your \"shouldFollowLinksInPage\" policy",page.getWebURL().getURL());
      }
      boolean noIndex=myController.getConfig().isRespectNoIndex() && page.getContentType() != null && page.getContentType().contains("html") && ((HtmlParseData)page.getParseData()).getMetaTagValue("robots").contains("noindex");
      if (!noIndex) {
        visit(page);
      }
    }
  }
 catch (  PageBiggerThanMaxSizeException e) {
    onPageBiggerThanMaxSize(curURL.getURL(),e.getPageSize());
  }
catch (  ParseException pe) {
    onParseError(curURL,pe);
  }
catch (  ContentFetchException|SocketTimeoutException cfe) {
    onContentFetchError(curURL);
    onContentFetchError(page);
  }
catch (  NotAllowedContentException nace) {
    logger.debug("Skipping: {} as it contains binary content which you configured not to crawl",curURL.getURL());
  }
catch (  IOException|InterruptedException|RuntimeException e) {
    onUnhandledException(curURL,e);
  }
 finally {
    if (fetchResult != null) {
      fetchResult.discardContentIfNotConsumed();
    }
  }
}
