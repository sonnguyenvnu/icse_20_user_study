/** 
 * Open's a splash window using the specified image.
 * @param imageURL The url of the splash image.
 */
public static void splash(URL imageURL,boolean hidpi){
  if (imageURL != null) {
    splash(Toolkit.getDefaultToolkit().createImage(imageURL),hidpi);
  }
}
