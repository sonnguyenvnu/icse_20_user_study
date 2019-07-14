/** 
 * <b>This function almost certainly does not do the thing you want it to.</b> The data path is handled differently on each platform, and should not be considered a location to write files. It should also not be assumed that this location can be read from or listed. This function is used internally as a possible location for reading files. It's still "public" as a holdover from earlier code. <p> Libraries should use createInput() to get an InputStream or createOutput() to get an OutputStream. sketchPath() can be used to get a location relative to the sketch. Again, <b>do not</b> use this to get relative locations of files. You'll be disappointed when your app runs on different platforms.
 */
public String dataPath(String where){
  return dataFile(where).getAbsolutePath();
}
