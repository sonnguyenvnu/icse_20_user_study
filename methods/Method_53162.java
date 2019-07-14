private static List<HttpParameter> toParamList(HttpParameter[] params){
  List<HttpParameter> paramList=new ArrayList<HttpParameter>(params.length);
  paramList.addAll(Arrays.asList(params));
  return paramList;
}
