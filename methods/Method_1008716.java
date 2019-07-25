public Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> execute(String name,T suggestion,IndexSearcher searcher,CharsRefBuilder spare) throws IOException {
  if (searcher.getIndexReader().numDocs() == 0) {
    return null;
  }
  return innerExecute(name,suggestion,searcher,spare);
}
