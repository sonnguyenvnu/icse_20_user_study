@Override public Category populate(PersistableCategory source,Category target,MerchantStore store,Language language) throws ConversionException {
  try {
    target.setMerchantStore(store);
    target.setCode(source.getCode());
    target.setSortOrder(source.getSortOrder());
    target.setVisible(source.isVisible());
    target.setFeatured(source.isFeatured());
    if (source.getParent() == null) {
      target.setParent(null);
      target.setLineage("/");
      target.setDepth(0);
    }
 else {
      Category parent=null;
      if (!StringUtils.isBlank(source.getParent().getCode())) {
        parent=categoryService.getByCode(store.getCode(),source.getParent().getCode());
      }
 else       if (source.getParent().getId() != null) {
        parent=categoryService.getById(source.getParent().getId());
      }
 else {
        throw new ConversionException("Category parent needs at least an id or a code for reference");
      }
      if (parent != null && parent.getMerchantStore().getId().intValue() != store.getId().intValue()) {
        throw new ConversionException("Store id does not belong to specified parent id");
      }
      if (parent != null) {
        target.setParent(parent);
        String lineage=parent.getLineage();
        int depth=parent.getDepth();
        target.setDepth(depth + 1);
        target.setLineage(new StringBuilder().append(lineage).append(parent.getId()).append("/").toString());
      }
    }
    if (!CollectionUtils.isEmpty(source.getChildren())) {
      for (      PersistableCategory cat : source.getChildren()) {
        Category persistCategory=this.populate(cat,new Category(),store,language);
        target.getCategories().add(persistCategory);
      }
    }
    if (!CollectionUtils.isEmpty(source.getDescriptions())) {
      List<com.salesmanager.core.model.catalog.category.CategoryDescription> descriptions=new ArrayList<com.salesmanager.core.model.catalog.category.CategoryDescription>();
      for (      CategoryDescription description : source.getDescriptions()) {
        com.salesmanager.core.model.catalog.category.CategoryDescription desc=new com.salesmanager.core.model.catalog.category.CategoryDescription();
        desc.setCategory(target);
        desc.setCategoryHighlight(description.getHighlights());
        desc.setDescription(description.getDescription());
        desc.setName(description.getName());
        desc.setMetatagDescription(description.getMetaDescription());
        desc.setMetatagTitle(description.getTitle());
        desc.setSeUrl(description.getFriendlyUrl());
        Language lang=languageService.getByCode(description.getLanguage());
        if (lang == null) {
          throw new ConversionException("Language is null for code " + description.getLanguage() + " use language ISO code [en, fr ...]");
        }
        desc.setLanguage(lang);
        descriptions.add(desc);
      }
      target.setDescriptions(descriptions);
    }
    return target;
  }
 catch (  Exception e) {
    throw new ConversionException(e);
  }
}
