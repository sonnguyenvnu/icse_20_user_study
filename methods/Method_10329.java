/** 
 * Adds a file to the request with both custom provided file content-type and file name
 * @param key            the key name for the new param.
 * @param file           the file to add.
 * @param contentType    the content type of the file, eg. application/json
 * @param customFileName file name to use instead of real file name
 * @throws FileNotFoundException throws if wrong File argument was passed
 */
public void put(String key,File file,String contentType,String customFileName) throws FileNotFoundException {
  if (file == null || !file.exists()) {
    throw new FileNotFoundException();
  }
  if (key != null) {
    fileParams.put(key,new FileWrapper(file,contentType,customFileName));
  }
}
