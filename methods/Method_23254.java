/** 
 * Returns a path inside the applet folder to save to. Like sketchPath(), but creates any in-between folders so that things save properly. <p/> All saveXxxx() functions use the path to the sketch folder, rather than its data folder. Once exported, the data folder will be found inside the jar file of the exported application or applet. In this case, it's not possible to save data into the jar file, because it will often be running from a server, or marked in-use if running from a local file system. With this in mind, saving to the data path doesn't make sense anyway. If you know you're running locally, and want to save to the data folder, use <TT>saveXxxx("data/blah.dat")</TT>.
 */
public String savePath(String where){
  if (where == null)   return null;
  String filename=sketchPath(where);
  createPath(filename);
  return filename;
}
