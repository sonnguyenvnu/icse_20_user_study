@ModelAttribute void beforeInvokingHandlerMethod(HttpServletRequest request){
  request.setAttribute("foo","bar");
}
