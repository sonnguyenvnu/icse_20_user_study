HttpParameter[] asHttpParameterArray(){
  ArrayList<HttpParameter> params=new ArrayList<HttpParameter>(12);
  appendParameter("q",query,params);
  appendParameter("lang",lang,params);
  appendParameter("locale",locale,params);
  appendParameter("max_id",maxId,params);
  appendParameter("count",count,params);
  appendParameter("since",since,params);
  appendParameter("since_id",sinceId,params);
  appendParameter("geocode",geocode,params);
  appendParameter("until",until,params);
  if (resultType != null) {
    params.add(new HttpParameter("result_type",resultType.name()));
  }
  params.add(WITH_TWITTER_USER_ID);
  HttpParameter[] paramArray=new HttpParameter[params.size()];
  return params.toArray(paramArray);
}
