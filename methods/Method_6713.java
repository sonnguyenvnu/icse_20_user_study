private TLRPC.Document getDocumentWithId(TLRPC.WebPage webPage,long id){
  if (webPage == null || webPage.cached_page == null) {
    return null;
  }
  if (webPage.document != null && webPage.document.id == id) {
    return webPage.document;
  }
  for (int a=0; a < webPage.cached_page.documents.size(); a++) {
    TLRPC.Document document=webPage.cached_page.documents.get(a);
    if (document.id == id) {
      return document;
    }
  }
  return null;
}
