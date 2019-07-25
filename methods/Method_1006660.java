@Override public ProductOption populate(PersistableProductOption source,ProductOption target,MerchantStore store,Language language) throws ConversionException {
  Validate.notNull(languageService,"Requires to set LanguageService");
  try {
    target.setMerchantStore(store);
    target.setProductOptionSortOrder(source.getOrder());
    target.setCode(source.getCode());
    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      Set<com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription> descriptions=new HashSet<com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription>();
      for (      ProductOptionDescription desc : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription description=new com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription();
        Language lang=languageService.getByCode(desc.getLanguage());
        if (lang == null) {
          throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
        }
        description.setLanguage(lang);
        description.setName(desc.getName());
        description.setTitle(desc.getTitle());
        description.setProductOption(target);
        descriptions.add(description);
      }
      target.setDescriptions(descriptions);
    }
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
  return target;
}
