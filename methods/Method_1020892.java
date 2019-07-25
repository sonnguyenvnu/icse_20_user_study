private String token(String token){
  String lcToken=token.toLowerCase().trim();
  if (lcToken.equals("left") || lcToken.equals("right") || lcToken.equals("inner") || lcToken.equals("outer") || lcToken.equals("inner")) {
    return token;
  }
  if (lcToken.equals("join")) {
    return token + " fetch";
  }
  String prefixName=WowWhereParser.root(token);
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
