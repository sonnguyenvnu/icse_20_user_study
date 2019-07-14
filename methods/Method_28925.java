public static int getDBIndex(URI uri){
  String[] pathSplit=uri.getPath().split("/",2);
  if (pathSplit.length > 1) {
    String dbIndexStr=pathSplit[1];
    if (dbIndexStr.isEmpty()) {
      return DEFAULT_DB;
    }
    return Integer.parseInt(dbIndexStr);
  }
 else {
    return DEFAULT_DB;
  }
}
