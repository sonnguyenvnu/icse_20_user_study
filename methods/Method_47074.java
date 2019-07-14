public static String parseDocumentFilePermission(DocumentFile file){
  String per="";
  if (file.canRead()) {
    per=per + "r";
  }
  if (file.canWrite()) {
    per=per + "w";
  }
  if (file.canWrite()) {
    per=per + "x";
  }
  return per;
}
