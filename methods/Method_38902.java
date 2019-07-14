/** 
 * Selected nodes using pre-parsed CSS selectors. Take in consideration collection type for results grouping order.
 */
public List<Node> select(final Collection<List<CssSelector>> selectorsCollection){
  List<Node> results=new ArrayList<>();
  for (  List<CssSelector> selectors : selectorsCollection) {
    processSelectors(results,selectors);
  }
  return results;
}
