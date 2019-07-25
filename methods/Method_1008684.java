String projection(final SearchContext searchContext){
  return (searchContext.request().extraParams() != null && searchContext.request().extraParams().containsKey(PROJECTION)) ? (String)searchContext.request().extraParams().get(PROJECTION) : null;
}
