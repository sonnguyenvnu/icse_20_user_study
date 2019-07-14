/** 
 * Absolute path to the sketch folder. Used to set the working directry of the sketch when running, i.e. so that saveFrame() goes to the right location when running from the PDE, instead of the same folder as the Processing.exe or the root of the user's home dir.
 */
public String getSketchPath(){
  return sketch.getFolder().getAbsolutePath();
}
