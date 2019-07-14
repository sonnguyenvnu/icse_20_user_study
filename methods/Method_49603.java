@Override public Long totals(RawQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  final Query q;
  try {
    final Analyzer analyzer=delegatingAnalyzerFor(query.getStore(),information);
    q=new NumericTranslationQueryParser(information.get(query.getStore()),"_all",analyzer).parse(query.getQuery());
  }
 catch (  final ParseException e) {
    throw new PermanentBackendException("Could not parse raw query: " + query.getQuery(),e);
  }
  try {
    final IndexSearcher searcher=((Transaction)tx).getSearcher(query.getStore());
    if (searcher == null)     return 0L;
    final long time=System.currentTimeMillis();
    query.setLimit(1);
    final TopDocs docs=searcher.search(q,1);
    log.debug("Executed query [{}] in {} ms",q,System.currentTimeMillis() - time);
    return docs.totalHits;
  }
 catch (  final IOException e) {
    throw new TemporaryBackendException("Could not execute Lucene query",e);
  }
}
