public boolean matches(String url){
  String[] urlParts=url.split("/");
  if (templateParts.length != urlParts.length) {
    return false;
  }
  for (int i=0; i < templateParts.length; i++) {
    boolean isVariable=isVariable(templateParts[i]);
    boolean areEqual=templateParts[i].equals(urlParts[i]);
    if (!isVariable && !areEqual) {
      return false;
    }
  }
  return true;
}
