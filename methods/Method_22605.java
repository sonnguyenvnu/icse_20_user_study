/** 
 * Get an image object from the mode folder. Or when prefixed with /lib, load it from the main /lib folder.
 */
public Image loadImage(String filename){
  ImageIcon icon=loadIcon(filename);
  if (icon != null) {
    return icon.getImage();
  }
  return null;
}
