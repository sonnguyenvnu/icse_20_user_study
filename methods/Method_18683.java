@NotNull @Override protected JavaParameterTableModel createParametersInfoModel(JavaMethodDescriptor descriptor){
  final PsiParameterList parameterList=descriptor.getMethod().getParameterList();
  return new JavaParameterTableModel(parameterList,myDefaultValueContext,this);
}
