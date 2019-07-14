@Override public Stream<RawQuery.Result<String>> query(RawQuery query,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
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
    if (searcher == null) {
      return Collections.unmodifiableList(new ArrayList<RawQuery.Result<String>>()).stream();
    }
    final long time=System.currentTimeMillis();
    final int offset=query.getOffset();
    int adjustedLimit=query.hasLimit() ? query.getLimit() : Integer.MAX_VALUE - 1;
    if (adjustedLimit < Integer.MAX_VALUE - 1 - offset)     adjustedLimit+=offset;
 else     adjustedLimit=Integer.MAX_VALUE - 1;
    final TopDocs docs;
    if (!query.getOrders().isEmpty()) {
      docs=searcher.search(q,adjustedLimit,getSortOrder(query.getOrders()));
    }
 else {
      docs=searcher.search(q,adjustedLimit);
    }
    log.debug("Executed query [{}] in {} ms",q,System.currentTimeMillis() - time);
    final List<RawQuery.Result<String>> result=new ArrayList<>(docs.scoreDocs.length);
    for (int i=offset; i < docs.scoreDocs.length; i++) {
      final IndexableField field=searcher.doc(docs.scoreDocs[i].doc).getField(DOCID);
      result.add(new RawQuery.Result<>(field == null ? null : field.stringValue(),docs.scoreDocs[i].score));
    }
    return result.stream();
  }
 catch (  final IOException e) {
    throw new TemporaryBackendException("Could not execute Lucene query",e);
  }
}
