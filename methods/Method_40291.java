/** 
 * If the module defines an  {@code __all__} variable, returns the stringelements of the variable's list value.
 * @return any exported name nodes found, or an empty list if none found
 */
public List<Str> getExportedNameNodes(Type t) throws Exception {
  List<Str> exports=new ArrayList<Str>();
  Binding all=t.getTable().lookupLocal("__all__");
  if (all == null) {
    return exports;
  }
  Def def=all.getSignatureNode();
  if (def == null) {
    return exports;
  }
  Node __all__=getDeepestNodeAtOffset(def.getStart());
  if (!(__all__ instanceof Name)) {
    return exports;
  }
  Node assign=__all__.getParent();
  if (!(assign instanceof Assign)) {
    return exports;
  }
  Node rvalue=((Assign)assign).rvalue;
  if (!(rvalue instanceof NList)) {
    return exports;
  }
  for (  Node elt : ((NList)rvalue).elts) {
    if (elt instanceof Str) {
      Str nstr=(Str)elt;
      if (nstr.getStr() != null) {
        exports.add(nstr);
      }
    }
  }
  return exports;
}
