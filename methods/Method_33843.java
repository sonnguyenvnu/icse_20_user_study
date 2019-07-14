/** 
 * ???????
 */
public void saveSearch(String keyword){
  if (!TextUtils.isEmpty(keyword)) {
    if (searchHistory == null) {
      searchHistory=getSearchHistory();
    }
    if (searchHistory != null) {
      if (searchHistory.size() > 0) {
        searchHistory.remove(keyword);
      }
      searchHistory.add(0,keyword);
      if (searchHistory.size() > 12) {
        searchHistory.remove(searchHistory.size() - 1);
      }
    }
    if (gson == null) {
      gson=new Gson();
    }
    SPUtils.putString(SEARCH_HISTORY,gson.toJson(searchHistory));
    history.setValue(searchHistory);
  }
}
