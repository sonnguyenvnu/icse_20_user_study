private CompletionProvider<CompletionParameters> typeCompletionProvider(){
  return new CompletionProvider<CompletionParameters>(){
    @Override protected void addCompletions(    @NotNull CompletionParameters parameters,    ProcessingContext context,    @NotNull CompletionResultSet result){
      PsiElement element=parameters.getPosition();
      PsiElement parentElement=PsiTreeUtil.findFirstParent(element,PsiClass.class::isInstance);
      if (parentElement == null) {
        return;
      }
      PsiClass parentClass=(PsiClass)parentElement;
      if (!LithoPluginUtils.isLithoSpec(parentClass)) {
        return;
      }
      PsiMethod onEventMethod=createOnClickEventMethod(element);
      result.addElement(createMethodAnnotationLookup(onEventMethod));
    }
    private PsiMethod createOnClickEventMethod(    PsiElement element){
      Project project=element.getProject();
      PsiClass clickEventClass=JavaPsiFacade.getInstance(project).findClass(LithoClassNames.CLICK_EVENT_CLASS_NAME,GlobalSearchScope.allScope(project));
      if (clickEventClass == null) {
        clickEventClass=JavaPsiFacade.getElementFactory(project).createClass(LithoClassNames.shortName(LithoClassNames.CLICK_EVENT_CLASS_NAME));
      }
      return OnEventGenerateUtils.createOnEventMethod(element,clickEventClass,Collections.emptyList());
    }
  }
;
}
