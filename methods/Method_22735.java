/** 
 * Called when the sketch folder name/location has changed. Called when renaming tab 0, the main code.
 */
public void setFolder(File sketchFolder){
  file=new File(sketchFolder,file.getName());
}
