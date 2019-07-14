private HttpParameter[] mergeParameters(HttpParameter[] params1,HttpParameter params2){
  if (params1 != null && params2 != null) {
    HttpParameter[] params=new HttpParameter[params1.length + 1];
    System.arraycopy(params1,0,params,0,params1.length);
    params[params.length - 1]=params2;
    return params;
  }
  if (null == params1 && null == params2) {
    return new HttpParameter[0];
  }
  if (params1 != null) {
    return params1;
  }
 else {
    return new HttpParameter[]{params2};
  }
}
