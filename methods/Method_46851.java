public HybridFileParcelable generateBaseFile(){
  HybridFileParcelable baseFile=new HybridFileParcelable(desc,permissions,date,longSize,isDirectory);
  baseFile.setMode(mode);
  baseFile.setName(title);
  return baseFile;
}
