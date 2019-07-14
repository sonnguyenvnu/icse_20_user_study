public static HybridFileParcelable generateBaseFile(File x,boolean showHidden){
  long size=0;
  if (!x.isDirectory())   size=x.length();
  HybridFileParcelable baseFile=new HybridFileParcelable(x.getPath(),parseFilePermission(x),x.lastModified(),size,x.isDirectory());
  baseFile.setName(x.getName());
  baseFile.setMode(OpenMode.FILE);
  if (showHidden) {
    return (baseFile);
  }
 else   if (!x.isHidden()) {
    return (baseFile);
  }
  return null;
}
