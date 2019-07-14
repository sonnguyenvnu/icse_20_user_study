public void trackSearchResults(final @NonNull String query,final int pageCount){
  if (pageCount == 1) {
    final Map<String,Object> params=new HashMap<String,Object>(){
{
        put("search_term",query);
      }
    }
;
    this.client.track(KoalaEvent.LOADED_SEARCH_RESULTS,params);
    this.client.track(KoalaEvent.DISCOVER_SEARCH_RESULTS_LEGACY,params);
  }
 else {
    final Map<String,Object> params=new HashMap<String,Object>(){
{
        put("search_term",query);
        put("page_count",pageCount);
      }
    }
;
    this.client.track(KoalaEvent.LOADED_MORE_SEARCH_RESULTS,params);
    this.client.track(KoalaEvent.DISCOVER_SEARCH_RESULTS_LOAD_MORE_LEGACY,params);
  }
}
