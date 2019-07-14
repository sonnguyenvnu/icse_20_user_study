private void processDefDebug(@NotNull Binding binding){
  int hash=binding.hashCode();
  if (binding.isURL() || binding.start < 0 || seenDef.contains(hash)) {
    return;
  }
  seenDef.add(hash);
  Style style=new Style(Style.Type.ANCHOR,binding.start,binding.end);
  style.message=binding.type.toString();
  style.url=binding.qname;
  style.id="" + Math.abs(binding.hashCode());
  Set<Node> refs=binding.refs;
  style.highlight=new ArrayList<>();
  for (  Node r : refs) {
    style.highlight.add(Integer.toString(Math.abs(r.hashCode())));
  }
  addFileStyle(binding.getFile(),style);
}
