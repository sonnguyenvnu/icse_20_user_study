/** 
 * Fills domain navigation.
 * @param dataModel the specified data model
 */
private void fillDomainNav(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills domain nav");
  try {
    dataModel.put(Domain.DOMAINS,domainCache.getDomains(Integer.MAX_VALUE));
  }
  finally {
    Stopwatchs.end();
  }
}
