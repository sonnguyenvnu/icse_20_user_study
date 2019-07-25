String path(){
  final String path=get(HttpHeaderNames.PATH);
  checkState(path != null,":path header does not exist.");
  return path;
}
