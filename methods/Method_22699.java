protected void load(String path){
  primaryFile=new File(path);
  String mainFilename=primaryFile.getName();
  int suffixLength=mode.getDefaultExtension().length() + 1;
  name=mainFilename.substring(0,mainFilename.length() - suffixLength);
  folder=new File(new File(path).getParent());
  disappearedWarning=false;
  load();
}
