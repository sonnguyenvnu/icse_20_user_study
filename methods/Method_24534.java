static protected void checkDir(String path,DefaultFontMapper mapper){
  File folder=new File(path);
  if (folder.exists()) {
    mapper.insertDirectory(path);
    traverseDir(folder,mapper);
  }
}
