/** 
 * Extracts the specified shared library or native resource from the classpath to a temporary directory.
 * @param name     the resource name
 * @param filename the resource filename
 * @param resource the classpath {@link URL} were the resource can be found
 * @return a {@link FileChannel} that has locked the resource file
 */
static FileChannel load(String name,String filename,URL resource){
  try {
    Path extractedFile;
    EXTRACT_PATH_LOCK.lock();
    try {
      if (extractPath != null) {
        extractedFile=extractPath.resolve(filename);
      }
 else {
        extractedFile=getExtractPath(filename,resource);
        if (Platform.get() != Platform.WINDOWS || workaroundJDK8195129(extractedFile)) {
          initExtractPath(extractPath=extractedFile.getParent());
        }
      }
    }
  finally {
      EXTRACT_PATH_LOCK.unlock();
    }
    return extract(extractedFile,resource);
  }
 catch (  Exception e) {
    throw new RuntimeException("\tFailed to extract " + name + " library",e);
  }
}
