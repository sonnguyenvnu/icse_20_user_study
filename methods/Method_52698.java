public EcmascriptNode<?> getExpression(){
  if (!isDefault()) {
    return (EcmascriptNode<?>)jjtGetChild(0);
  }
 else {
    return null;
  }
}
