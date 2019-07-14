private HostDirectives fetchDirectives(URL url) throws IOException, InterruptedException {
  WebURL robotsTxtUrl=new WebURL();
  String host=getHost(url);
  String port=((url.getPort() == url.getDefaultPort()) || (url.getPort() == -1)) ? "" : (":" + url.getPort());
  String proto=url.getProtocol();
  robotsTxtUrl.setURL(proto + "://" + host + port + "/robots.txt");
  HostDirectives directives=null;
  PageFetchResult fetchResult=null;
  try {
    for (int redir=0; redir < 3; ++redir) {
      fetchResult=pageFetcher.fetchPage(robotsTxtUrl);
      int status=fetchResult.getStatusCode();
      if ((status == HttpStatus.SC_MULTIPLE_CHOICES || status == HttpStatus.SC_MOVED_PERMANENTLY || status == HttpStatus.SC_MOVED_TEMPORARILY || status == HttpStatus.SC_SEE_OTHER || status == HttpStatus.SC_TEMPORARY_REDIRECT || status == 308) && fetchResult.getMovedToUrl() != null) {
        robotsTxtUrl.setURL(fetchResult.getMovedToUrl());
        fetchResult.discardContentIfNotConsumed();
      }
 else {
        break;
      }
    }
    if (fetchResult.getStatusCode() == HttpStatus.SC_OK) {
      Page page=new Page(robotsTxtUrl);
      fetchResult.fetchContent(page,10_000 * 1024);
      if (Util.hasPlainTextContent(page.getContentType())) {
        String content;
        if (page.getContentCharset() == null) {
          content=new String(page.getContentData());
        }
 else {
          content=new String(page.getContentData(),page.getContentCharset());
        }
        directives=RobotstxtParser.parse(content,config);
      }
 else       if (page.getContentType().contains("html")) {
        String content=new String(page.getContentData());
        directives=RobotstxtParser.parse(content,config);
      }
 else {
        logger.warn("Can't read this robots.txt: {}  as it is not written in plain text, " + "contentType: {}",robotsTxtUrl.getURL(),page.getContentType());
      }
    }
 else {
      logger.debug("Can't read this robots.txt: {}  as it's status code is {}",robotsTxtUrl.getURL(),fetchResult.getStatusCode());
    }
  }
 catch (  SocketException|UnknownHostException|SocketTimeoutException|NoHttpResponseException se) {
    logger.trace("robots.txt probably does not exist.",se);
  }
catch (  PageBiggerThanMaxSizeException pbtms) {
    logger.error("Error occurred while fetching (robots) url: {}, {}",robotsTxtUrl.getURL(),pbtms.getMessage());
  }
catch (  IOException e) {
    logger.error("Error occurred while fetching (robots) url: " + robotsTxtUrl.getURL(),e);
  }
catch (  InterruptedException|RuntimeException e) {
    if (crawlConfig.isHaltOnError()) {
      throw e;
    }
 else {
      logger.error("Error occurred while fetching (robots) url: " + robotsTxtUrl.getURL(),e);
    }
  }
 finally {
    if (fetchResult != null) {
      fetchResult.discardContentIfNotConsumed();
    }
  }
  if (directives == null) {
    directives=new HostDirectives(config);
  }
synchronized (host2directivesCache) {
    if (host2directivesCache.size() == config.getCacheSize()) {
      String minHost=null;
      long minAccessTime=Long.MAX_VALUE;
      for (      Map.Entry<String,HostDirectives> entry : host2directivesCache.entrySet()) {
        long entryAccessTime=entry.getValue().getLastAccessTime();
        if (entryAccessTime < minAccessTime) {
          minAccessTime=entryAccessTime;
          minHost=entry.getKey();
        }
      }
      host2directivesCache.remove(minHost);
    }
    host2directivesCache.put(host,directives);
  }
  return directives;
}
