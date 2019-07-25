private boolean log(HttpServletRequest request){
  String path=request.getRequestURL().toString();
  if (path.startsWith("/")) {
    return true;
  }
  return false;
}
