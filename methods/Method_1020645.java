@Override public Object run(){
  RequestContext ctx=RequestContext.getCurrentContext();
  Map<String,List<String>> params=ctx.getRequestQueryParams();
  if (params == null) {
    return null;
  }
  List<String> passList=params.get(PASSWORD);
  if (CollectionUtils.isEmpty(passList)) {
    return null;
  }
  String password=passList.get(0);
  if (StringUtils.isNotBlank(password)) {
    try {
      password=decryptAES(password,key);
    }
 catch (    Exception e) {
      log.error("??????:{}",password);
    }
    List pass=new ArrayList();
    pass.add(password.trim());
    params.put(PASSWORD,pass);
  }
  ctx.setRequestQueryParams(params);
  return null;
}
