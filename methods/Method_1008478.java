private static void index(final List<ParseContext.Document> docs,final IndexWriter indexWriter) throws IOException {
  if (docs.size() > 1) {
    indexWriter.addDocuments(docs);
  }
 else {
    indexWriter.addDocument(docs.get(0));
  }
}
