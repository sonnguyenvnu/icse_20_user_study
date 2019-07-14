private List<String> getSearchHistory(){
  try {
    String details=SPUtils.getString(SEARCH_HISTORY,"");
    if (!TextUtils.isEmpty(details)) {
      if (gson == null) {
        gson=new Gson();
      }
      return gson.fromJson(details,new TypeToken<List<String>>(){
      }
.getType());
    }
 else {
      return new ArrayList<String>();
    }
  }
 catch (  Exception e) {
    return new ArrayList<String>();
  }
}
