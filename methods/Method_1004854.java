private String unquote(String ident){
  if (ident.startsWith("`") || ident.startsWith("\"")) {
    return ident.substring(1,ident.length() - 1);
  }
 else   return ident;
}
