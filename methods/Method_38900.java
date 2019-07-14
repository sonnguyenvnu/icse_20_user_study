public boolean match(final String query){
  Collection<List<CssSelector>> selectorsCollection=CSSelly.parse(query);
  return match(selectorsCollection);
}
