/** 
 * Get an ImageIcon from the Processing 'lib' folder.
 * @since 3.0a6
 */
static public ImageIcon getLibIcon(String filename){
  File file=Platform.getContentFile("lib/" + filename);
  if (!file.exists()) {
    return null;
  }
  return new ImageIcon(file.getAbsolutePath());
}
