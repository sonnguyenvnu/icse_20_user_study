public Str getDocstring(){
  Node parent=node.parent;
  if ((parent instanceof FunctionDef && ((FunctionDef)parent).name == node) || (parent instanceof ClassDef && ((ClassDef)parent).name == node)) {
    return parent.getDocString();
  }
 else {
    return node.getDocString();
  }
}
