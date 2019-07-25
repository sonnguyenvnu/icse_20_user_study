/** 
 * Refreshes the local content by synchronizing with the remote marketplace.
 */
public synchronized void refresh(){
  XmlDocumentReader<Marketplace> reader=new MarketplaceXMLReader();
  try {
    Marketplace result=reader.readFromXML(url);
    cachedNodes=result.categories[0].nodes;
  }
 catch (  Exception e) {
    if (cachedNodes == null) {
      logger.warn("Failed downloading Marketplace entries: {}",e.getMessage());
      logger.warn("Retrying again in a minute");
      this.executorService.schedule(() -> refresh(),retry_delay,TimeUnit.SECONDS);
    }
 else {
      logger.debug("Cannot access IoT Marketplace - will continue to use cached results: {}",e.getMessage());
    }
  }
}
