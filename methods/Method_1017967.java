@Nonnull @Override public SearchResultsDTO search(@Nonnull final String term){
  return get("/flow/search-results",ImmutableMap.of("q",term),SearchResultsEntity.class).getSearchResultsDTO();
}
