public String localize(){
  return String.format("\"%s\" (%s, %s)",getQuery(),getLocalizedCaseSensitiveDescription(),getLocalizedRegularExpressionDescription());
}
