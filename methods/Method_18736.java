@Nullable static TypeName getReturnType(PsiClass psiClass){
  PsiAnnotation eventAnnotation=AnnotationUtil.findAnnotation(psiClass,"com.facebook.litho.annotations.Event");
  PsiNameValuePair returnTypePair=AnnotationUtil.findDeclaredAttribute(eventAnnotation,"returnType");
  if (returnTypePair == null) {
    return TypeName.VOID;
  }
  PsiClassObjectAccessExpression returnTypeClassExpression=(PsiClassObjectAccessExpression)returnTypePair.getValue();
  PsiType returnTypeType=returnTypeClassExpression.getOperand().getType();
  return PsiTypeUtils.getTypeName(returnTypeType);
}
