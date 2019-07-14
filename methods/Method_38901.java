public boolean match(final Collection<List<CssSelector>> selectorsCollection){
  for (  List<CssSelector> selectors : selectorsCollection) {
    for (    CssSelector cssSelector : selectors) {
      if (!cssSelector.accept(rootNode)) {
        return false;
      }
    }
  }
  return true;
}
