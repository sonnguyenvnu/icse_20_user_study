/** 
 * Parses selector string. Returns <code>null</code> if no selector can be parsed.
 */
public List<CssSelector> parse(){
  try {
    lexer.yylex();
    if (lexer.selectors.isEmpty()) {
      return null;
    }
    CssSelector last=lexer.selectors.get(lexer.selectors.size() - 1);
    if (last.getCombinator() == Combinator.DESCENDANT) {
      last.setCombinator(null);
    }
    CssSelector prevCssSelector=null;
    for (    CssSelector cssSelector : lexer.selectors) {
      if (prevCssSelector != null) {
        cssSelector.setPrevCssSelector(prevCssSelector);
      }
      prevCssSelector=cssSelector;
    }
    return lexer.selectors;
  }
 catch (  IOException ioex) {
    throw new CSSellyException(ioex);
  }
}
