private String token(String token){
  String prefixName=root(token);
  if (columns.contains(prefixName)) {
    if (prefixName.equals(alias)) {
      return token;
    }
 else {
      return alias + "." + token;
    }
  }
  return token;
}
