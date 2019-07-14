@Override public Cursor queryChildDocuments(String parentDocumentId,String[] projection,String sortOrder) throws FileNotFoundException {
  final MatrixCursor result=new MatrixCursor(projection != null ? projection : DEFAULT_DOCUMENT_PROJECTION);
  final File parent=getFileForDocId(parentDocumentId);
  for (  File file : parent.listFiles()) {
    if (!file.getName().startsWith(".")) {
      includeFile(result,null,file);
    }
  }
  return result;
}
