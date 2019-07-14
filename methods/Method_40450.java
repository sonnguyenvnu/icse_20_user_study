private void highlightDocString(Str node){
  String s=sourceString(node.start(),node.end());
  DocStringParser dsp=new DocStringParser(s,node,linker);
  dsp.setResolveReferences(true);
  styles.addAll(dsp.highlight());
}
