/** 
 * Set the title of the PDE window based on the current sketch, i.e. something like "sketch_070752a - Processing 0126"
 */
public void updateTitle(){
  setTitle(sketch.getName() + " | Processing " + Base.getVersionName());
  if (!sketch.isUntitled()) {
    File sketchFile=sketch.getMainFile();
    getRootPane().putClientProperty("Window.documentFile",sketchFile);
  }
 else {
    getRootPane().putClientProperty("Window.documentFile",null);
  }
}
