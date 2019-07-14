/** 
 * Gets the top-level data source supplier to be used by a controller. 
 */
protected Supplier<DataSource<IMAGE>> obtainDataSourceSupplier(final DraweeController controller,final String controllerId){
  if (mDataSourceSupplier != null) {
    return mDataSourceSupplier;
  }
  Supplier<DataSource<IMAGE>> supplier=null;
  if (mImageRequest != null) {
    supplier=getDataSourceSupplierForRequest(controller,controllerId,mImageRequest);
  }
 else   if (mMultiImageRequests != null) {
    supplier=getFirstAvailableDataSourceSupplier(controller,controllerId,mMultiImageRequests,mTryCacheOnlyFirst);
  }
  if (supplier != null && mLowResImageRequest != null) {
    List<Supplier<DataSource<IMAGE>>> suppliers=new ArrayList<>(2);
    suppliers.add(supplier);
    suppliers.add(getDataSourceSupplierForRequest(controller,controllerId,mLowResImageRequest));
    supplier=IncreasingQualityDataSourceSupplier.create(suppliers,false);
  }
  if (supplier == null) {
    supplier=DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION);
  }
  return supplier;
}
