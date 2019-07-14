HttpParameter[] asHttpParameterArray(){
  ArrayList<HttpParameter> params=new ArrayList<HttpParameter>();
  if (location != null) {
    appendParameter("lat",location.getLatitude(),params);
    appendParameter("long",location.getLongitude(),params);
  }
  if (ip != null) {
    appendParameter("ip",ip,params);
  }
  appendParameter("accuracy",accuracy,params);
  appendParameter("query",query,params);
  appendParameter("granularity",granularity,params);
  appendParameter("max_results",maxResults,params);
  HttpParameter[] paramArray=new HttpParameter[params.size()];
  return params.toArray(paramArray);
}
