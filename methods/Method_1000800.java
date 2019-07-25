public void render(HttpServletRequest req,HttpServletResponse resp,Object obj){
  HttpEnhanceResponse info=this.info.clone();
  if (null != obj) {
    if (obj instanceof HttpStatusException) {
      HttpStatusException hse=((HttpStatusException)obj);
      info.updateCode(hse.getStatus(),null);
      info.updateBody(hse.getMessage());
    }
 else     if (obj instanceof Map<?,?>) {
      info.update((Map<?,?>)obj);
    }
 else     if (obj instanceof CharSequence) {
      info.updateBy(obj.toString());
    }
  }
  info.render(resp);
}
