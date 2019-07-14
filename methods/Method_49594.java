@Override public void restore(Map<String,Map<String,List<IndexEntry>>> documents,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  writerLock.lock();
  try {
    for (    final Map.Entry<String,Map<String,List<IndexEntry>>> stores : documents.entrySet()) {
      final String store=stores.getKey();
      final IndexWriter writer=getWriter(store,information);
      final IndexReader reader=DirectoryReader.open(writer,true,true);
      final IndexSearcher searcher=new IndexSearcher(reader);
      for (      final Map.Entry<String,List<IndexEntry>> entry : stores.getValue().entrySet()) {
        final String docID=entry.getKey();
        final List<IndexEntry> content=entry.getValue();
        if (content == null || content.isEmpty()) {
          if (log.isTraceEnabled())           log.trace("Deleting document [{}]",docID);
          writer.deleteDocuments(new Term(DOCID,docID));
          continue;
        }
        final Pair<Document,Map<String,Shape>> docAndGeo=retrieveOrCreate(docID,searcher);
        addToDocument(store,docID,docAndGeo.getKey(),content,docAndGeo.getValue(),information);
        writer.updateDocument(new Term(DOCID,docID),docAndGeo.getKey());
      }
      writer.commit();
    }
    tx.commit();
  }
 catch (  final IOException e) {
    throw new TemporaryBackendException("Could not update Lucene index",e);
  }
 finally {
    writerLock.unlock();
  }
}
