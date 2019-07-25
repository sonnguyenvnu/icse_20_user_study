@Override public boolean supports(MarketplaceExtension ext){
  return ext.getType().equals(MarketplaceExtension.EXT_TYPE_RULE_TEMPLATE) && ext.getPackageFormat().equals(MarketplaceExtension.EXT_FORMAT_JSON);
}
