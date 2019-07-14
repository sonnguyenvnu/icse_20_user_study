private TLRPC.Document getDocumentWithId(long id){
  if (currentPage == null || currentPage.cached_page == null) {
    return null;
  }
  if (currentPage.document != null && currentPage.document.id == id) {
    return currentPage.document;
  }
  for (int a=0; a < currentPage.cached_page.documents.size(); a++) {
    TLRPC.Document document=currentPage.cached_page.documents.get(a);
    if (document.id == id) {
      return document;
    }
  }
  return null;
}
