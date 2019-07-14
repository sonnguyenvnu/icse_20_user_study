/** 
 * Return the binding for  {@code qname}, or  {@code null} if not known.
 */
public Binding lookupFirstBinding(String qname){
  List<Binding> lb=allBindings.get(qname);
  if (lb != null && lb.size() > 0) {
    return lb.get(0);
  }
 else {
    return null;
  }
}
