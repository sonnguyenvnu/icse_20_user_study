@Override public String getDocumentType(String documentId) throws FileNotFoundException {
  File file=getFileForDocId(documentId);
  return getMimeType(file);
}
