public EcmascriptNode<?> getParam(int index){
  int paramIndex=index;
  if (node.getFunctionName() != null) {
    paramIndex=index + 1;
  }
  return (EcmascriptNode<?>)jjtGetChild(paramIndex);
}
