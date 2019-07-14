private HttpParameter[] mergeParameters(HttpParameter[] params1,HttpParameter[] params2){
  if (params1 != null && params2 != null) {
    HttpParameter[] params=new HttpParameter[params1.length + params2.length];
    System.arraycopy(params1,0,params,0,params1.length);
    System.arraycopy(params2,0,params,params1.length,params2.length);
    return params;
  }
  if (null == params1 && null == params2) {
    return new HttpParameter[0];
  }
  if (params1 != null) {
    return params1;
  }
 else {
    return params2;
  }
}
