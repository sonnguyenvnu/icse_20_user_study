private static void suggest(IndexSearcher searcher,CompletionQuery query,TopSuggestDocsCollector collector) throws IOException {
  query=(CompletionQuery)query.rewrite(searcher.getIndexReader());
  Weight weight=query.createWeight(searcher,collector.needsScores(),1f);
  for (  LeafReaderContext context : searcher.getIndexReader().leaves()) {
    BulkScorer scorer=weight.bulkScorer(context);
    if (scorer != null) {
      try {
        scorer.score(collector.getLeafCollector(context),context.reader().getLiveDocs());
      }
 catch (      CollectionTerminatedException e) {
      }
    }
  }
}
