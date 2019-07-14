private static boolean hasNonNullConstantValue(LocalVariableNode node){
  if (node.getElement() instanceof VariableElement) {
    VariableElement element=(VariableElement)node.getElement();
    return (element.getConstantValue() != null);
  }
  return false;
}
