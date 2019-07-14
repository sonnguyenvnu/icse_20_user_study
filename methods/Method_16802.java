private String getPath(String savePath,String filename,String suffix){
  return PathFormat.parse(savePath + suffix,filename);
}
