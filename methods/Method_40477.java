public Binding putBinding(Binding b){
  if (b == null) {
    throw new IllegalArgumentException("null binding arg");
  }
  String qname=b.getQname();
  if (qname == null || qname.isEmpty()) {
    throw new IllegalArgumentException("Null/empty qname: " + b);
  }
  Binding existing=findBinding(b);
  if (existing == null) {
    addBinding(qname,b);
    return b;
  }
 else {
    existing.setType(UnionType.union(existing.getType(),b.getType()));
    return existing;
  }
}
