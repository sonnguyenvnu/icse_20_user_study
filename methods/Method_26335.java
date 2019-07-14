private Symbol attribIdent(String name){
  Attr attr=Attr.instance(context);
  TreeMaker tm=visitorState.getTreeMaker();
  return attr.attribIdent(tm.Ident(visitorState.getName(name)),compilationUnit);
}
