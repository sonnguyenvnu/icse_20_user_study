/** 
 * Gets the directory to use to store the given key
 * @param resourceId the id of the file we're going to store
 * @return the directory to store the file in
 */
private String getSubdirectoryPath(String resourceId){
  String subdirectory=String.valueOf(Math.abs(resourceId.hashCode() % SHARDING_BUCKET_COUNT));
  return mVersionDirectory + File.separator + subdirectory;
}
