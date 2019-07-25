@Override public ValueEnumeration elements(final Ordering ordering){
  if (ordering == Ordering.NORMALIZED) {
    final Value enumerated=this.toSetEnum();
    if (enumerated != null) {
      return ((Enumerable)enumerated.normalize()).elements();
    }
  }
  return elements();
}
