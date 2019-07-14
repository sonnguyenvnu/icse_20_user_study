/** 
 * Get the compression-free extension for this filename.
 * @param filename The filename to check
 * @return an extension, skipping past .gz if it's present
 */
static public String checkExtension(String filename){
  if (filename.toLowerCase().endsWith(".gz")) {
    filename=filename.substring(0,filename.length() - 3);
  }
  int dotIndex=filename.lastIndexOf('.');
  if (dotIndex != -1) {
    return filename.substring(dotIndex + 1).toLowerCase();
  }
  return null;
}
