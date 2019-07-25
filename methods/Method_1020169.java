@ExceptionHandler(Exception.class) public Object handler(HttpServletRequest request,HttpServletResponse response,Exception e){
  e.printStackTrace();
  if (isAjax(request)) {
    return this.responseByAjax(request,response,e);
  }
  return this.responseByWeb(request,response,e);
}
