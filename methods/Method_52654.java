public EcmascriptNode<?> getElement(){
  if (jjtGetNumChildren() > 1) {
    return (EcmascriptNode<?>)jjtGetChild(1);
  }
  return null;
}
