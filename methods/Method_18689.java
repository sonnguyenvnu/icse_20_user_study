private String doCalculateSignature(PsiMethod method){
  if (nameToParameter == null) {
    return "";
  }
  final StringBuilder buffer=new StringBuilder();
  final PsiModifierList modifierList=method.getModifierList();
  final PsiElement[] modifiers=modifierList.getChildren();
  for (int i=0, size=modifiers.length; i < size; i++) {
    final PsiElement modifier=modifiers[i];
    if (modifier instanceof PsiWhiteSpace) {
      continue;
    }
    buffer.append(modifier.getText());
    if (i == 0 && modifier instanceof PsiAnnotation) {
      buffer.append("\n");
    }
 else {
      buffer.append(" ");
    }
  }
  final PsiType returnType=method.getReturnType();
  if (returnType != null) {
    buffer.append(returnType.getPresentableText()).append(" ");
  }
  buffer.append(getMethodName()).append("(");
  final int lineBreakIdx=buffer.lastIndexOf("\n");
  final String indent=StringUtil.repeatSymbol(' ',lineBreakIdx >= 0 ? buffer.length() - lineBreakIdx - 1 : buffer.length());
  final List<ParameterTableModelItemBase<ParameterInfoImpl>> currentTableItems=myParametersTableModel.getItems();
  for (int i=0; i < currentTableItems.size(); i++) {
    final ParameterTableModelItemBase<ParameterInfoImpl> item=currentTableItems.get(i);
    if (i > 0) {
      buffer.append(",");
      buffer.append("\n");
      buffer.append(indent);
    }
    final String itemName=item.parameter.getName();
    final String itemType=item.typeCodeFragment.getText();
    final PsiParameter parameter=getInitialMethodParameter(itemName,itemType);
    if (parameter != null) {
      for (      PsiElement annotation : parameter.getAnnotations()) {
        buffer.append(annotation.getText()).append(" ");
      }
    }
 else {
      buffer.append("@").append(LithoClassNames.shortName(LithoClassNames.PARAM_ANNOTATION_NAME)).append(" ");
    }
    buffer.append(itemType).append(" ").append(itemName);
  }
  buffer.append(")");
  return buffer.toString();
}
