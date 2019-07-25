@Override public ReadableCategory populate(final Category source,final ReadableCategory target,final MerchantStore store,final Language language) throws ConversionException {
  Validate.notNull(source,"Category must not be null");
  target.setLineage(source.getLineage());
  if (source.getDescriptions() != null && source.getDescriptions().size() > 0) {
    CategoryDescription description=source.getDescription();
    if (source.getDescriptions().size() > 1) {
      for (      final CategoryDescription desc : source.getDescriptions()) {
        if (desc.getLanguage().getCode().equals(language.getCode())) {
          description=desc;
          break;
        }
      }
    }
    if (description != null) {
      final com.salesmanager.shop.model.catalog.category.CategoryDescription desc=new com.salesmanager.shop.model.catalog.category.CategoryDescription();
      desc.setFriendlyUrl(description.getSeUrl());
      desc.setName(description.getName());
      desc.setId(source.getId());
      desc.setDescription(description.getName());
      desc.setKeyWords(description.getMetatagKeywords());
      desc.setHighlights(description.getCategoryHighlight());
      desc.setTitle(description.getMetatagTitle());
      desc.setMetaDescription(description.getMetatagDescription());
      target.setDescription(desc);
    }
  }
  if (source.getParent() != null) {
    final com.salesmanager.shop.model.catalog.category.Category parent=new com.salesmanager.shop.model.catalog.category.Category();
    parent.setCode(source.getParent().getCode());
    parent.setId(source.getParent().getId());
    target.setParent(parent);
  }
  target.setCode(source.getCode());
  target.setId(source.getId());
  if (source.getDepth() != null) {
    target.setDepth(source.getDepth());
  }
  target.setSortOrder(source.getSortOrder());
  target.setVisible(source.isVisible());
  target.setFeatured(source.isFeatured());
  return target;
}
