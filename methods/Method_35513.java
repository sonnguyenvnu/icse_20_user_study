public PathParams parse(String url){
  PathParams pathParams=new PathParams();
  String[] urlParts=url.split("/");
  if (templateParts.length != urlParts.length) {
    throw new IllegalArgumentException(url + " does not match " + templateString);
  }
  for (int i=0; i < templateParts.length; i++) {
    Matcher matcher=PATH_VARIABLE_REGEX.matcher(templateParts[i]);
    boolean areEqual=templateParts[i].equals(urlParts[i]);
    checkArgument(areEqual || matcher.matches(),url + " does not match " + templateString);
    if (matcher.matches()) {
      String variableName=getVariableName(templateParts[i]);
      pathParams.put(variableName,urlParts[i]);
    }
  }
  return pathParams;
}
