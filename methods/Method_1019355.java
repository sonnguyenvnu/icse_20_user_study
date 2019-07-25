@SuppressWarnings("OptionalGetWithoutIsPresent") private ESProductDO convert(ProductSpuDetailBO spu){
  ProductSpuDetailBO.Sku sku=spu.getSkus().stream().min(Comparator.comparing(ProductSpuDetailBO.Sku::getPrice)).get();
  CalcSkuPriceBO calSkuPriceResult=cartService.calcSkuPrice(sku.getId());
  return ProductSearchConvert.INSTANCE.convert(spu,calSkuPriceResult);
}
