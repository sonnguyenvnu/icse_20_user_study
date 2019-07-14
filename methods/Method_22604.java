/** 
 * Get an ImageIcon object from the Mode folder. Or when prefixed with /lib, load it from the main /lib folder.
 * @since 3.0a6
 */
public ImageIcon loadIcon(String filename){
  if (filename.startsWith("/lib/")) {
    return Toolkit.getLibIcon(filename.substring(5));
  }
  File file=new File(folder,filename);
  if (!file.exists()) {
    return null;
  }
  return new ImageIcon(file.getAbsolutePath());
}
