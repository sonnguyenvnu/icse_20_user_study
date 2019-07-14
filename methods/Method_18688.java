/** 
 * @return either an error message or null if no errors are found. This method has side effect ofcreating a new PsiMethod with chosen fields and method name.
 */
@Nullable private String validateAndCreateMethod(){
  String methodName=getMethodName();
  if (!PsiNameHelper.getInstance(myProject).isIdentifier(methodName)) {
    return RefactoringMessageUtil.getIncorrectIdentifierMessage(methodName);
  }
  final PsiElementFactory factory=JavaPsiFacade.getInstance(myProject).getElementFactory();
  final PsiMethod oldMethod=myMethod.getMethod();
  final PsiMethod newMethod=factory.createMethod(methodName,oldMethod.getReturnType());
  final List<ParameterTableModelItemBase<ParameterInfoImpl>> tableModelItems=myParametersTableModel.getItems();
  final PsiParameterList parameterList=newMethod.getParameterList();
  for (  final ParameterTableModelItemBase<ParameterInfoImpl> item : tableModelItems) {
    final String parameterName=item.parameter.getName();
    if (!PsiNameHelper.getInstance(myProject).isIdentifier(parameterName)) {
      return RefactoringMessageUtil.getIncorrectIdentifierMessage(parameterName);
    }
    final PsiType parameterType;
    try {
      parameterType=((PsiTypeCodeFragment)item.typeCodeFragment).getType();
    }
 catch (    PsiTypeCodeFragment.TypeSyntaxException e) {
      return RefactoringBundle.message("changeSignature.wrong.type.for.parameter",item.typeCodeFragment.getText(),parameterName);
    }
catch (    PsiTypeCodeFragment.NoTypeException e) {
      return RefactoringBundle.message("changeSignature.no.type.for.parameter","return",parameterName);
    }
    if (PsiTypesUtil.hasUnresolvedComponents(parameterType)) {
      return RefactoringBundle.message("changeSignature.cannot.resolve.parameter.type");
    }
    if (parameterType instanceof PsiEllipsisType) {
      return "Don`t use varargs type for " + parameterName;
    }
    PsiParameter parameter=getInitialMethodParameter(parameterName,parameterType.getPresentableText());
    if (parameter == null) {
      parameter=factory.createParameter(parameterName,parameterType);
      final PsiModifierList parameterModifierList=parameter.getModifierList();
      if (parameterModifierList == null) {
        continue;
      }
      parameterModifierList.addAnnotation(LithoClassNames.PARAM_ANNOTATION_NAME);
    }
    parameterList.add(parameter);
  }
  final PsiModifierList modifierList=newMethod.getModifierList();
  for (  PsiElement modifier : oldMethod.getModifierList().getChildren()) {
    modifierList.add(modifier);
  }
  modifierList.setModifierProperty(PsiModifier.PACKAGE_LOCAL,true);
  this.newMethod=newMethod;
  return null;
}
