@Override public Cursor querySearchDocuments(String rootId,String query,String[] projection) throws FileNotFoundException {
  final MatrixCursor result=new MatrixCursor(projection != null ? projection : DEFAULT_DOCUMENT_PROJECTION);
  final File parent=getFileForDocId(rootId);
  final LinkedList<File> pending=new LinkedList<>();
  pending.add(parent);
  final int MAX_SEARCH_RESULTS=50;
  while (!pending.isEmpty() && result.getCount() < MAX_SEARCH_RESULTS) {
    final File file=pending.removeFirst();
    boolean isInsideHome;
    try {
      isInsideHome=file.getCanonicalPath().startsWith(TermuxService.HOME_PATH);
    }
 catch (    IOException e) {
      isInsideHome=true;
    }
    final boolean isHidden=file.getName().startsWith(".");
    if (isInsideHome && !isHidden) {
      if (file.isDirectory()) {
        Collections.addAll(pending,file.listFiles());
      }
 else {
        if (file.getName().toLowerCase().contains(query)) {
          includeFile(result,null,file);
        }
      }
    }
  }
  return result;
}
