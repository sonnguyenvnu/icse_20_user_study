private String stripParams(String path){
  int paramsStart=path.indexOf("?");
  String stripped=paramsStart == -1 ? path : path.substring(0,paramsStart);
  if (stripped.startsWith("/")) {
    stripped=stripped.substring(1);
  }
  return stripped;
}
