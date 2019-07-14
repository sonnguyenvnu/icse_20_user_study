/** 
 * Puts a  {@link DatapointParcelable} into a listMethod call is synchronized so as to avoid modifying the list by  {@link ServiceWatcherUtil#handlerThread} while {@link MainActivity#runOnUiThread(Runnable)}is executing the callbacks in  {@link ProcessViewerFragment}
 */
private synchronized void putDataPackage(DatapointParcelable dataPackage){
  getDataPackages().add(dataPackage);
}
