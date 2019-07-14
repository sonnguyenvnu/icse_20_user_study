@Override public Stream<String> query(IndexQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  final String store=query.getStore();
  final LuceneCustomAnalyzer delegatingAnalyzer=delegatingAnalyzerFor(store,information);
  final SearchParams searchParams=convertQuery(query.getCondition(),information.get(store),delegatingAnalyzer);
  try {
    final IndexSearcher searcher=((Transaction)tx).getSearcher(query.getStore());
    if (searcher == null) {
      return Collections.unmodifiableList(new ArrayList<String>()).stream();
    }
    Query q=searchParams.getQuery();
    if (null == q)     q=new MatchAllDocsQuery();
    final long time=System.currentTimeMillis();
    final TopDocs docs=searcher.search(q,query.hasLimit() ? query.getLimit() : Integer.MAX_VALUE - 1,getSortOrder(query));
    log.debug("Executed query [{}] in {} ms",q,System.currentTimeMillis() - time);
    final List<String> result=new ArrayList<>(docs.scoreDocs.length);
    for (int i=0; i < docs.scoreDocs.length; i++) {
      final IndexableField field=searcher.doc(docs.scoreDocs[i].doc).getField(DOCID);
      result.add(field == null ? null : field.stringValue());
    }
    return result.stream();
  }
 catch (  final IOException e) {
    throw new TemporaryBackendException("Could not execute Lucene query",e);
  }
}
