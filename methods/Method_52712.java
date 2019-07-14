public EcmascriptNode<?> getInitializer(){
  if (jjtGetNumChildren() > 0) {
    return (EcmascriptNode<?>)jjtGetChild(1);
  }
 else {
    return null;
  }
}
