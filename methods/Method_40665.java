public Str findDocString(){
  Node fullNode=node;
  if (kind == Kind.CLASS) {
    while (fullNode != null && !(fullNode instanceof org.yinwang.rubysonar.ast.Class)) {
      fullNode=fullNode.parent;
    }
  }
 else   if (kind == Kind.METHOD || kind == Kind.CLASS_METHOD) {
    while (fullNode != null && !(fullNode instanceof Function)) {
      fullNode=fullNode.parent;
    }
  }
 else   if (kind == Kind.MODULE) {
    while (fullNode != null && !(fullNode instanceof Module)) {
      fullNode=fullNode.parent;
    }
  }
  if (fullNode instanceof org.yinwang.rubysonar.ast.Class) {
    return ((org.yinwang.rubysonar.ast.Class)fullNode).docstring;
  }
 else   if (fullNode instanceof Function) {
    return ((Function)fullNode).docstring;
  }
 else   if (fullNode instanceof Module) {
    return ((Module)fullNode).docstring;
  }
 else {
    return null;
  }
}
