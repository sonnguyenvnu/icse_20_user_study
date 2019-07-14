public EcmascriptNode<?> getTarget(){
  if (jjtGetNumChildren() > 0) {
    return (EcmascriptNode<?>)jjtGetChild(0);
  }
  return null;
}
