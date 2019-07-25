/** 
 * Creates a  {@link FileLocation} instance for the specified file.
 * @param url The URL of a file.
 * @return The file's location.
 */
public static FileLocation create(URL url){
  if ("file".equalsIgnoreCase(url.getProtocol())) {
    return new FileFileLocation(new File(url.getPath()));
  }
  return new URLFileLocation(url);
}
