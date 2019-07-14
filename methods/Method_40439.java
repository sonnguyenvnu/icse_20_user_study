private void processDef(Def def,Binding nb){
  if (def == null || def.isURL()) {
    return;
  }
  StyleRun style=new StyleRun(StyleRun.Type.ANCHOR,def.getStart(),def.getLength());
  style.message=nb.getQname() + " :: " + nb.getType();
  style.url=nb.getQname();
  style.id="" + Math.abs(def.hashCode());
  Set<Ref> refs=nb.getRefs();
  style.highlight=new HashSet<String>();
  for (  Ref r : refs) {
    style.highlight.add(Integer.toString(Math.abs(r.hashCode())));
  }
  addFileStyle(def.getFile(),style);
}
