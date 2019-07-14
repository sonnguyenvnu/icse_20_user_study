public EcmascriptNode<?> getFinallyBlock(){
  if (!hasFinally()) {
    return null;
  }
  return (EcmascriptNode<?>)jjtGetChild(jjtGetNumChildren() - 1);
}
