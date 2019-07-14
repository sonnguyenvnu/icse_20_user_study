/** 
 * Returns the  {@link Uri} associated with the last {@link DataSource#open} call. If redirectionoccurred, this is the redirected uri. Must only be called after the load completed, failed, or was canceled.
 * @see DataSource#getUri()
 */
public final Uri getUri(){
  return dataSource.getLastOpenedUri();
}
