public String render(HttpServletRequest request,HttpServletResponse response,Model model,String tplName,KeyPrefix prefix,String key){
  if (!pageCacheEnable) {
    return tplName;
  }
  String html=redisService.get(prefix,key,String.class);
  if (!StringUtils.isEmpty(html)) {
    out(response,html);
    return null;
  }
  WebContext ctx=new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
  html=thymeleafViewResolver.getTemplateEngine().process(tplName,ctx);
  if (!StringUtils.isEmpty(html)) {
    redisService.set(prefix,key,html);
  }
  out(response,html);
  return null;
}
