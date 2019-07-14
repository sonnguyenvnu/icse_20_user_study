public static HybridFileParcelable generateBaseFile(DocumentFile file){
  long size=0;
  if (!file.isDirectory())   size=file.length();
  HybridFileParcelable baseFile=new HybridFileParcelable(file.getName(),parseDocumentFilePermission(file),file.lastModified(),size,file.isDirectory());
  baseFile.setName(file.getName());
  baseFile.setMode(OpenMode.OTG);
  return baseFile;
}
