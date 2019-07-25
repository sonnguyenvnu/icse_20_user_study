/** 
 * Returns whether or not this format matches the supplied filename. Returns false if the filename is a URI, otherwise matches against the file extensions returned by  {@link #getSupportedExtensions()}.
 * @param filename the filename to match
 * @return <code>true</code> if the format matches, <code>false</code> otherwise.
 */
public boolean match(String filename){
  if (filename == null) {
    return false;
  }
  filename=filename.toLowerCase(Locale.ROOT);
  String[] supportedExtensions=getSupportedExtensions();
  if (supportedExtensions != null) {
    String protocol=FileUtil.getProtocol(filename);
    if (protocol != null) {
      return false;
    }
    for (    String extension : supportedExtensions) {
      String ext=extension.toLowerCase(Locale.ROOT);
      if (filename.endsWith("." + ext)) {
        setMatchedExtension(ext);
        return true;
      }
    }
  }
  return false;
}
