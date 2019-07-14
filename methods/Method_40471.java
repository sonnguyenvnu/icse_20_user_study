public Binding lookupBindingAtOffset(String qname,int offset){
  List<Binding> lb=allBindings.get(qname);
  for (  Binding b : lb) {
    for (    Def d : b.getDefs()) {
      if (d.getStart() == offset) {
        return b;
      }
    }
  }
  return null;
}
