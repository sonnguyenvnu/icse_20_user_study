public Object visit(EcmascriptNode<?> node,Object data){
  dump(node,(String)data);
  if (recurse) {
    for (int i=0; i < node.jjtGetNumChildren(); i++) {
      visit((EcmascriptNode<?>)node.jjtGetChild(i),data + " ");
    }
    return data;
  }
 else {
    return data;
  }
}
