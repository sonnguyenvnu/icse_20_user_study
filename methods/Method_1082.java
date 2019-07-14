protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(final DraweeController controller,String controllerId,REQUEST[] imageRequests,boolean tryBitmapCacheOnlyFirst){
  List<Supplier<DataSource<IMAGE>>> suppliers=new ArrayList<>(imageRequests.length * 2);
  if (tryBitmapCacheOnlyFirst) {
    for (int i=0; i < imageRequests.length; i++) {
      suppliers.add(getDataSourceSupplierForRequest(controller,controllerId,imageRequests[i],CacheLevel.BITMAP_MEMORY_CACHE));
    }
  }
  for (int i=0; i < imageRequests.length; i++) {
    suppliers.add(getDataSourceSupplierForRequest(controller,controllerId,imageRequests[i]));
  }
  return FirstAvailableDataSourceSupplier.create(suppliers);
}
