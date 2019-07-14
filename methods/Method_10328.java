/** 
 * Adds files array to the request with both custom provided file content-type and files name
 * @param key            the key name for the new param.
 * @param files          the files array to add.
 * @param contentType    the content type of the file, eg. application/json
 * @param customFileName file name to use instead of real file name
 * @throws FileNotFoundException throws if wrong File argument was passed
 */
public void put(String key,File files[],String contentType,String customFileName) throws FileNotFoundException {
  if (key != null) {
    List<FileWrapper> fileWrappers=new ArrayList<FileWrapper>();
    for (    File file : files) {
      if (file == null || !file.exists()) {
        throw new FileNotFoundException();
      }
      fileWrappers.add(new FileWrapper(file,contentType,customFileName));
    }
    fileArrayParams.put(key,fileWrappers);
  }
}
