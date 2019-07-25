@Override public void install(MarketplaceExtension ext) throws MarketplaceHandlerException {
  String url=ext.getDownloadUrl();
  try {
    String template=getTemplate(url);
    marketplaceRuleTemplateProvider.addTemplateAsJSON(ext.getId(),template);
  }
 catch (  IOException e) {
    logger.error("Rule template from marketplace cannot be downloaded: {}",e.getMessage());
    throw new MarketplaceHandlerException("Template cannot be downloaded.");
  }
catch (  Exception e) {
    logger.error("Rule template from marketplace is invalid: {}",e.getMessage());
    throw new MarketplaceHandlerException("Template is not valid.");
  }
}
