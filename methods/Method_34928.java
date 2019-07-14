/** 
 * Please note that in the case of a bad URL, TRUE will be returned
 * @throws InterruptedException
 * @throws IOException
 */
public boolean allows(WebURL webURL) throws IOException, InterruptedException {
  if (!config.isEnabled()) {
    return true;
  }
  try {
    URL url=new URL(webURL.getURL());
    String host=getHost(url);
    String path=url.getPath();
    HostDirectives directives=host2directivesCache.get(host);
    if (directives != null && directives.needsRefetch()) {
synchronized (host2directivesCache) {
        host2directivesCache.remove(host);
        directives=null;
      }
    }
    if (directives == null) {
      directives=fetchDirectives(url);
    }
    return directives.allows(path);
  }
 catch (  MalformedURLException e) {
    logger.error("Bad URL in Robots.txt: " + webURL.getURL(),e);
  }
  logger.warn("RobotstxtServer: default: allow",webURL.getURL());
  return true;
}
