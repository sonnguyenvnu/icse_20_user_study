private Pair<Document,Map<String,Shape>> retrieveOrCreate(String docID,IndexSearcher searcher) throws IOException {
  final Document doc;
  final TopDocs hits=searcher.search(new TermQuery(new Term(DOCID,docID)),10);
  final Map<String,Shape> geoFields=Maps.newHashMap();
  if (hits.scoreDocs.length > 1)   throw new IllegalArgumentException("More than one document found for document id: " + docID);
  if (hits.scoreDocs.length == 0) {
    if (log.isTraceEnabled())     log.trace("Creating new document for [{}]",docID);
    doc=new Document();
    doc.add(new StringField(DOCID,docID,Field.Store.YES));
  }
 else {
    if (log.isTraceEnabled())     log.trace("Updating existing document for [{}]",docID);
    final int docId=hits.scoreDocs[0].doc;
    doc=searcher.doc(docId);
    for (    final IndexableField field : doc.getFields()) {
      if (field.stringValue().startsWith(GEOID)) {
        try {
          geoFields.put(field.name(),Geoshape.fromWkt(field.stringValue().substring(GEOID.length())).getShape());
        }
 catch (        final java.text.ParseException e) {
          throw new IllegalArgumentException("Geoshape was not parsable");
        }
      }
    }
  }
  return new ImmutablePair<>(doc,geoFields);
}
