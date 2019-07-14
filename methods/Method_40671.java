private void processDef(@NotNull Binding binding){
  String qname=binding.qname;
  int hash=binding.hashCode();
  if (binding.start < 0 || seenDef.contains(hash)) {
    return;
  }
  seenDef.add(hash);
  Style style=new Style(Style.Type.ANCHOR,binding.start,binding.end);
  style.message=binding.type.toString();
  style.url=binding.qname;
  style.id=qname;
  addFileStyle(binding.file,style);
}
