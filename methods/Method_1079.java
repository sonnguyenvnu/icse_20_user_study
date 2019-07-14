/** 
 * Validates the parameters before building a controller. 
 */
protected void validate(){
  Preconditions.checkState((mMultiImageRequests == null) || (mImageRequest == null),"Cannot specify both ImageRequest and FirstAvailableImageRequests!");
  Preconditions.checkState((mDataSourceSupplier == null) || (mMultiImageRequests == null && mImageRequest == null && mLowResImageRequest == null),"Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
}
