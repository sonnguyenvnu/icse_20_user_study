/** 
 * Enriches search hits and completion suggestion hits from <code>sortedDocs</code> using <code>fetchResultsArr</code>, merges suggestions, aggregations and profile results Expects sortedDocs to have top search docs across all shards, optionally followed by top suggest docs for each named completion suggestion ordered by suggestion name
 */
public InternalSearchResponse merge(boolean ignoreFrom,ReducedQueryPhase reducedQueryPhase,Collection<? extends SearchPhaseResult> fetchResults,IntFunction<SearchPhaseResult> resultsLookup){
  if (reducedQueryPhase.isEmptyResult) {
    return InternalSearchResponse.empty();
  }
  ScoreDoc[] sortedDocs=reducedQueryPhase.scoreDocs;
  SearchHits hits=getHits(reducedQueryPhase,ignoreFrom,fetchResults,resultsLookup);
  if (reducedQueryPhase.suggest != null) {
    if (!fetchResults.isEmpty()) {
      int currentOffset=hits.getHits().length;
      for (      CompletionSuggestion suggestion : reducedQueryPhase.suggest.filter(CompletionSuggestion.class)) {
        final List<CompletionSuggestion.Entry.Option> suggestionOptions=suggestion.getOptions();
        for (int scoreDocIndex=currentOffset; scoreDocIndex < currentOffset + suggestionOptions.size(); scoreDocIndex++) {
          ScoreDoc shardDoc=sortedDocs[scoreDocIndex];
          SearchPhaseResult searchResultProvider=resultsLookup.apply(shardDoc.shardIndex);
          if (searchResultProvider == null) {
            continue;
          }
          FetchSearchResult fetchResult=searchResultProvider.fetchResult();
          final int index=fetchResult.counterGetAndIncrement();
          assert index < fetchResult.hits().getHits().length : "not enough hits fetched. index [" + index + "] length: " + fetchResult.hits().getHits().length;
          SearchHit hit=fetchResult.hits().getHits()[index];
          CompletionSuggestion.Entry.Option suggestOption=suggestionOptions.get(scoreDocIndex - currentOffset);
          hit.score(shardDoc.score);
          hit.shard(fetchResult.getSearchShardTarget());
          suggestOption.setHit(hit);
        }
        currentOffset+=suggestionOptions.size();
      }
      assert currentOffset == sortedDocs.length : "expected no more score doc slices";
    }
  }
  return reducedQueryPhase.buildResponse(hits);
}
