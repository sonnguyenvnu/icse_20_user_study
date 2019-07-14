/** 
 * Read from a file with a bunch of attribute/value pairs that are separated by = and ignore comments with #. Changed in 3.x to return null (rather than empty hash) if no file, and changed return type to StringDict instead of Map or HashMap.
 */
static public StringDict readSettings(File inputFile){
  if (!inputFile.exists()) {
    Messages.loge(inputFile + " does not exist inside readSettings()");
    return null;
  }
  String lines[]=PApplet.loadStrings(inputFile);
  if (lines == null) {
    System.err.println("Could not read " + inputFile);
    return null;
  }
  return readSettings(inputFile.toString(),lines);
}
