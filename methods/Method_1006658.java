@Override public ReadableCategory convert(Category source,MerchantStore store,Language language){
  ReadableCategory target=new ReadableCategory();
  Optional<com.salesmanager.shop.model.catalog.category.CategoryDescription> categoryDescription=getCategoryDescription(source,language,target);
  categoryDescription.ifPresent(target::setDescription);
  Optional<com.salesmanager.shop.model.catalog.category.Category> parentCategory=createParentCategory(source);
  parentCategory.ifPresent(target::setParent);
  Optional.ofNullable(source.getDepth()).ifPresent(target::setDepth);
  target.setLineage(source.getLineage());
  target.setCode(source.getCode());
  target.setId(source.getId());
  target.setSortOrder(source.getSortOrder());
  target.setVisible(source.isVisible());
  target.setFeatured(source.isFeatured());
  return target;
}
