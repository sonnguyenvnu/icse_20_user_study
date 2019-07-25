/** 
 * Check whether there is one or more documents matching the provided query.
 */
public static boolean exists(IndexSearcher searcher,Query query) throws IOException {
  final Weight weight=searcher.createNormalizedWeight(query,false);
  for (  LeafReaderContext context : searcher.getIndexReader().leaves()) {
    final Scorer scorer=weight.scorer(context);
    if (scorer == null) {
      continue;
    }
    final Bits liveDocs=context.reader().getLiveDocs();
    final DocIdSetIterator iterator=scorer.iterator();
    for (int doc=iterator.nextDoc(); doc != DocIdSetIterator.NO_MORE_DOCS; doc=iterator.nextDoc()) {
      if (liveDocs == null || liveDocs.get(doc)) {
        return true;
      }
    }
  }
  return false;
}
