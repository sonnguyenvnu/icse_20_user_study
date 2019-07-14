/** 
 * Fills header.
 * @param context   the specified request context
 * @param dataModel the specified data model
 */
private void fillHeader(final RequestContext context,final Map<String,Object> dataModel){
  fillMinified(dataModel);
  dataModel.put(Common.STATIC_RESOURCE_VERSION,Latkes.getStaticResourceVersion());
  dataModel.put("esEnabled",Symphonys.ES_ENABLED);
  dataModel.put("algoliaEnabled",Symphonys.ALGOLIA_ENABLED);
  dataModel.put("algoliaAppId",Symphonys.ALGOLIA_APP_ID);
  dataModel.put("algoliaSearchKey",Symphonys.ALGOLIA_SEARCH_KEY);
  dataModel.put("algoliaIndex",Symphonys.ALGOLIA_INDEX);
  fillPersonalNav(dataModel);
  fillLangs(dataModel);
  fillSideAd(dataModel);
  fillHeaderBanner(dataModel);
  fillSideTips(dataModel);
  fillSideBreezemoons(dataModel);
  fillDomainNav(dataModel);
  dataModel.put(Common.CSRF_TOKEN,Sessions.getCSRFToken(context));
}
