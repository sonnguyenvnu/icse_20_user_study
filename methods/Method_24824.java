public void addDocumentListener(Document doc){
  if (doc != null)   doc.addDocumentListener(sketchChangedListener);
}
