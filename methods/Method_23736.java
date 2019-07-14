static public String extensionOptions(boolean loading,String filename,String options){
  String extension=PApplet.checkExtension(filename);
  if (extension != null) {
    for (    String possible : loading ? loadExtensions : saveExtensions) {
      if (extension.equals(possible)) {
        if (options == null) {
          return extension;
        }
 else {
          return extension + "," + options;
        }
      }
    }
  }
  return options;
}
