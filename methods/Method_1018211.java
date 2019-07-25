public List<SearchData> parsesearchdata(){
  final List<SearchData> searchArray=new ArrayList<SearchData>();
  SearchData searchData=null;
  try {
    JSONObject jsonobject=new JSONObject(result);
    JSONArray jsonArray=jsonobject.getJSONArray("results");
    for (int i=0; i < jsonArray.length(); i++) {
      searchData=new SearchData();
      String type_name, type, id, movie_poster, date, extra;
      id=jsonArray.getJSONObject(i).getString("id");
      date=jsonArray.getJSONObject(i).getString("release_date");
      movie_poster="http://image.tmdb.org/t/p/w185" + jsonArray.getJSONObject(i).getString("poster_path");
      type_name=jsonArray.getJSONObject(i).getString("original_title");
      type="movie";
      if (!(movie_poster.equals("null") && date.equals("null"))) {
        searchData.setId(id);
        searchData.setDate(date);
        searchData.setType(type);
        searchData.setMovie(type_name);
        searchData.setPoster(movie_poster);
        searchArray.add(searchData);
      }
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return searchArray;
}
