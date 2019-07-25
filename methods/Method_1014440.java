@Override public void uninstall(MarketplaceExtension ext) throws MarketplaceHandlerException {
  marketplaceRuleTemplateProvider.remove(ext.getId());
}
