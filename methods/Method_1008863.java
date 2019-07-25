@Override public List<Object> apply(Object o){
  if (o instanceof P) {
    P p=(P)o;
    if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
      sectPrList.add(p.getPPr().getSectPr());
    }
  }
  return null;
}
