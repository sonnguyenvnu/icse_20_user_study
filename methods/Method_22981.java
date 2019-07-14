/** 
 * Callback for the folder selector. 
 */
public void sketchbookCallback(File file){
  if (file != null) {
    sketchbookLocationField.setText(file.getAbsolutePath());
  }
}
