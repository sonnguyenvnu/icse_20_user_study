HttpParameter[] asHttpParameterArray(HttpParameter stallWarningsParam){
  ArrayList<HttpParameter> params=new ArrayList<HttpParameter>();
  params.add(new HttpParameter("count",count));
  if (follow != null && follow.length > 0) {
    params.add(new HttpParameter("follow",StringUtil.join(follow)));
  }
  if (track != null && track.length > 0) {
    params.add(new HttpParameter("track",StringUtil.join(track)));
  }
  if (locations != null && locations.length > 0) {
    params.add(new HttpParameter("locations",toLocationsString(locations)));
  }
  if (language != null && language.length > 0) {
    params.add(new HttpParameter("language",StringUtil.join(language)));
  }
  if (filterLevel != null) {
    params.add(new HttpParameter("filter_level",filterLevel));
  }
  params.add(stallWarningsParam);
  HttpParameter[] paramArray=new HttpParameter[params.size()];
  return params.toArray(paramArray);
}
