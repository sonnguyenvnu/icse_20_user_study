/** 
 * Closes the window
 */
public void handleClose(){
  Preferences.save();
  if (newSketchbook) {
    File folder=new File(Preferences.getSketchbookPath()).getParentFile();
    PApplet.selectFolder(Language.text("preferences.sketchbook_location.popup"),"sketchbookCallback",folder,this,this);
  }
  dispose();
}
