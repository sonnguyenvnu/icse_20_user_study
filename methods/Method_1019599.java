/** 
 * Creates a  {@link FileLocation} instance for the specified local file.
 * @param fileFullPath The full path to a local file.
 * @return The file's location.
 */
public static FileLocation create(String fileFullPath){
  if (fileFullPath.startsWith("http://") || fileFullPath.startsWith("https://") || fileFullPath.startsWith("ftp://")) {
    try {
      return new URLFileLocation(new URL(fileFullPath));
    }
 catch (    MalformedURLException mue) {
      throw new IllegalArgumentException("Not a valid URL: " + fileFullPath,mue);
    }
  }
  return new FileFileLocation(new File(fileFullPath));
}
