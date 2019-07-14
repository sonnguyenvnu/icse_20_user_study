static public String getFormat(String fileName,String mimeType){
  String fileNameFormat=getFormatFromFileName(fileName);
  if (mimeType != null) {
    mimeType=mimeType.split(";")[0];
  }
  String mimeTypeFormat=mimeType == null ? null : getFormatFromMimeType(mimeType);
  if (mimeTypeFormat == null) {
    return fileNameFormat;
  }
 else   if (fileNameFormat == null) {
    return mimeTypeFormat;
  }
 else   if (mimeTypeFormat.startsWith(fileNameFormat)) {
    return mimeTypeFormat;
  }
 else {
    return fileNameFormat;
  }
}
