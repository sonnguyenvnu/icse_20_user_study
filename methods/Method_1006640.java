@Override public void create(Product product) throws ServiceException {
  this.saveOrUpdate(product);
  searchService.index(product.getMerchantStore(),product);
}
