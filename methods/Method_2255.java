private static Supplier<DataSource<CloseableReference<CloseableImage>>> getFirstAvailableDataSourceSupplier(final ImagePipeline imagePipeline,final Object callerContext,final RequestListener requestListener,ImageRequest[] imageRequests,boolean tryBitmapCacheOnlyFirst){
  List<Supplier<DataSource<CloseableReference<CloseableImage>>>> suppliers=new ArrayList<>(imageRequests.length * 2);
  if (tryBitmapCacheOnlyFirst) {
    for (int i=0; i < imageRequests.length; i++) {
      suppliers.add(getImageRequestDataSourceSupplier(imagePipeline,imageRequests[i],callerContext,ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE,requestListener));
    }
  }
  for (int i=0; i < imageRequests.length; i++) {
    suppliers.add(getImageRequestDataSourceSupplier(imagePipeline,imageRequests[i],callerContext,requestListener));
  }
  return FirstAvailableDataSourceSupplier.create(suppliers);
}
