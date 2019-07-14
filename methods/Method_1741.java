/** 
 * Gets the directory to use to store the given key
 * @param resourceId the id of the file we're going to store
 * @return the directory to store the file in
 */
private File getSubdirectory(String resourceId){
  return new File(getSubdirectoryPath(resourceId));
}
