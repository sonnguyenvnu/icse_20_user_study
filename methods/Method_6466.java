public static String getDocumentExtension(TLRPC.Document document){
  String fileName=getDocumentFileName(document);
  int idx=fileName.lastIndexOf('.');
  String ext=null;
  if (idx != -1) {
    ext=fileName.substring(idx + 1);
  }
  if (ext == null || ext.length() == 0) {
    ext=document.mime_type;
  }
  if (ext == null) {
    ext="";
  }
  ext=ext.toUpperCase();
  return ext;
}
