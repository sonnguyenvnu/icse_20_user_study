@Nullable public Set<Binding> lookupAttr(String attr){
  if (looked.contains(this)) {
    return null;
  }
 else {
    Set<Binding> b=lookupLocal(attr);
    if (b != null) {
      return b;
    }
 else {
      if (supers != null && !supers.isEmpty()) {
        looked.add(this);
        for (        State p : supers) {
          b=p.lookupAttr(attr);
          if (b != null) {
            looked.remove(this);
            return b;
          }
        }
        looked.remove(this);
        return null;
      }
 else {
        return null;
      }
    }
  }
}
