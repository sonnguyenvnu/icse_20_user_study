@NotNull private LookupElementBuilder createMethodAnnotationLookup(PsiMethod method){
  Icon icon=method.getIcon(Iconable.ICON_FLAG_VISIBILITY);
  LookupElementBuilder elementBuilder=LookupElementBuilder.create(method).withInsertHandler((insertionContext,item) -> {
    insertionContext.getDocument().deleteString(insertionContext.getStartOffset() - 1,insertionContext.getTailOffset());
    insertionContext.commitDocument();
    new MethodGenerateHandler(method).invoke(insertionContext.getProject(),insertionContext.getEditor(),insertionContext.getFile());
    LithoLoggerProvider.getEventLogger().log(EventLogger.EVENT_ON_EVENT_COMPLETION);
    ComponentGenerateUtils.updateLayoutComponent(insertionContext.getFile());
  }
).appendTailText(" {...}",true).withTypeText("LayoutSpec").withIcon(icon);
  PsiAnnotation[] annotations=method.getAnnotations();
  if (annotations.length > 0) {
    String annotationName=annotations[0].getQualifiedName();
    if (annotationName != null) {
      annotationName=LithoClassNames.shortName(annotationName);
      elementBuilder=elementBuilder.withLookupString(annotationName);
    }
  }
  return elementBuilder;
}
