@Async @SuppressWarnings("rawtypes") public void index(MerchantStore store,Product product) throws ServiceException {
  if (configuration.getProperty(INDEX_PRODUCTS) == null || configuration.getProperty(INDEX_PRODUCTS).equals(Constants.FALSE)) {
    return;
  }
  FinalPrice price=pricingService.calculateProductPrice(product);
  Set<ProductDescription> descriptions=product.getDescriptions();
  for (  ProductDescription description : descriptions) {
    StringBuilder collectionName=new StringBuilder();
    collectionName.append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).append(UNDERSCORE).append(store.getCode().toLowerCase());
    IndexProduct index=new IndexProduct();
    index.setId(String.valueOf(product.getId()));
    index.setStore(store.getCode().toLowerCase());
    index.setLang(description.getLanguage().getCode());
    index.setAvailable(product.isAvailable());
    index.setDescription(description.getDescription());
    index.setName(description.getName());
    if (product.getManufacturer() != null) {
      index.setManufacturer(String.valueOf(product.getManufacturer().getId()));
    }
    if (price != null) {
      index.setPrice(price.getFinalPrice().doubleValue());
    }
    index.setHighlight(description.getProductHighlight());
    if (!StringUtils.isBlank(description.getMetatagKeywords())) {
      String[] tags=description.getMetatagKeywords().split(",");
      @SuppressWarnings("unchecked") List<String> tagsList=new ArrayList(Arrays.asList(tags));
      index.setTags(tagsList);
    }
    Set<Category> categories=product.getCategories();
    if (!CollectionUtils.isEmpty(categories)) {
      List<String> categoryList=new ArrayList<String>();
      for (      Category category : categories) {
        categoryList.add(category.getCode());
      }
      index.setCategories(categoryList);
    }
    String jsonString=index.toJSONString();
    try {
      searchService.index(jsonString,collectionName.toString(),new StringBuilder().append(PRODUCT_INDEX_NAME).append(UNDERSCORE).append(description.getLanguage().getCode()).toString());
    }
 catch (    Exception e) {
      throw new ServiceException("Cannot index product id [" + product.getId() + "], " + e.getMessage(),e);
    }
  }
}
