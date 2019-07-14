/** 
 * Adds comment to the given method "// An event handler ContextClassName.methodName(c, parameterName)
 */
public static void addComment(PsiClass contextClass,PsiMethod method){
  final Project project=contextClass.getProject();
  final PsiElementFactory factory=JavaPsiFacade.getElementFactory(project);
  final StringBuilder builder=new StringBuilder("// An event handler ").append(LithoPluginUtils.getLithoComponentNameFromSpec(contextClass.getName())).append(".").append(method.getName()).append("(").append(CONTEXT_PARAMETER_NAME);
  for (  PsiParameter parameter : method.getParameterList().getParameters()) {
    if (LithoPluginUtils.isParam(parameter)) {
      builder.append(", ").append(parameter.getName());
    }
  }
  builder.append(")");
  final PsiComment comment=factory.createCommentFromText(builder.toString(),method);
  method.addBefore(comment,method.getModifierList());
}
