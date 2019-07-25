@Override public List<Object> apply(Object o){
  if (firstSectPr == null && (o instanceof P)) {
    P p=(P)o;
    if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
      enclosingP=(P)o;
      firstSectPr=p.getPPr().getSectPr();
    }
  }
  return null;
}
