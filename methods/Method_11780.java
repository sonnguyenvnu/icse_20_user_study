/** 
 * Create a new MaxCore from a serialized file stored at storedResults
 * @deprecated use storedLocally()
 */
@Deprecated public static MaxCore forFolder(String folderName){
  return storedLocally(new File(folderName));
}
