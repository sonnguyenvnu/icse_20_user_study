/** 
 * Returns a file of either a folder or a containing archive.
 */
public static File toContainerFile(final URL url){
  String protocol=url.getProtocol();
  if (protocol.equals(FILE_PROTOCOL)) {
    return toFile(url);
  }
  String path=url.getPath();
  return new File(URI.create(path.substring(ZERO,path.lastIndexOf("!/"))));
}
