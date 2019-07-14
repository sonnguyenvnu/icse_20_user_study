/** 
 * Updates existing generated Component file from the given Spec file.
 * @param layoutSpecFile file containing {@link com.facebook.litho.annotations.LayoutSpec} class.
 */
public static void updateLayoutComponent(PsiFile layoutSpecFile){
  LithoLoggerProvider.getEventLogger().log(EventLogger.EVENT_UPDATE_COMPONENT);
  PsiClass layoutSpecCls=PsiTreeUtil.findChildOfType(layoutSpecFile,PsiClass.class);
  SpecModel model=createLayoutModel(layoutSpecCls);
  if (model == null) {
    return;
  }
  updateComponent(layoutSpecCls.getProject(),layoutSpecCls.getQualifiedName(),model);
}
