/** 
 * Returns a DataSource supplier that will on get submit the request for execution and return a DataSource representing the pending results of the task.
 * @param imageRequest the request to submit (what to execute).
 * @param callerContext the caller context of the caller of data source supplier
 * @param requestLevel which level to look down until for the image
 * @return a DataSource representing pending results and completion of the request
 */
public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest,final Object callerContext,final ImageRequest.RequestLevel requestLevel){
  return new Supplier<DataSource<CloseableReference<CloseableImage>>>(){
    @Override public DataSource<CloseableReference<CloseableImage>> get(){
      return fetchDecodedImage(imageRequest,callerContext,requestLevel);
    }
    @Override public String toString(){
      return Objects.toStringHelper(this).add("uri",imageRequest.getSourceUri()).toString();
    }
  }
;
}
