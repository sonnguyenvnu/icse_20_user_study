static protected String getBasePath(PApplet parent,String filename){
  File file=new File(parent.dataPath(filename));
  if (!file.exists()) {
    file=parent.sketchFile(filename);
  }
  String absolutePath=file.getAbsolutePath();
  return absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
}
