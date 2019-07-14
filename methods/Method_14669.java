protected String getCommandKey(HttpServletRequest request){
  String path=request.getPathInfo().substring("/command/".length());
  int slash1=path.indexOf('/');
  if (slash1 >= 0) {
    int slash2=path.indexOf('/',slash1 + 1);
    if (slash2 > 0) {
      path=path.substring(0,slash2);
    }
  }
  return path;
}
