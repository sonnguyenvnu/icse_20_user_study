public static Query evaluate(final HttpServletRequest request){
  try {
    request.setCharacterEncoding("UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
  }
  Map<String,String> qm=getQueryMap(request.getQueryString());
  Query post=new Query(request);
  post.initGET(qm);
  return post;
}
