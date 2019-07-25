@Override public boolean supports(MarketplaceExtension ext){
  return SUPPORTED_EXT_TYPES.contains(ext.getType()) && ext.getPackageFormat().equals(MarketplaceExtension.EXT_FORMAT_BUNDLE);
}
