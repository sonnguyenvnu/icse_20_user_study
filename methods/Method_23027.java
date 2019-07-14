/** 
 * Callback for the folder selector, used when user chooses a new sketchbook for Processing 3
 * @param folder the path to the new sketcbook
 */
public void sketchbookCallback(File folder){
  if (folder != null) {
    if (base != null) {
      base.setSketchbookFolder(folder);
    }
 else {
      System.out.println("user selected " + folder);
    }
  }
}
