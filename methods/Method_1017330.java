@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(XmlPatterns.psiElement().withParent(DoctrineMetadataPattern.getFieldType()),psiElement -> new MyTypeGotoCompletionProvider(psiElement){
    @Nullable @Override protected String getElementText(    @NotNull PsiElement element){
      return GotoCompletionUtil.getXmlAttributeValue(element);
    }
  }
);
  registrar.register(YamlElementPatternHelper.getOrmSingleLineScalarKey("type"),psiElement -> new MyTypeGotoCompletionProvider(psiElement){
    @Nullable @Override protected String getElementText(    @NotNull PsiElement element){
      String text=PsiElementUtils.trimQuote(element.getText());
      if (StringUtils.isBlank(text)) {
        return null;
      }
      return text;
    }
  }
);
  registrar.register(XmlPatterns.psiElement().withParent(XmlPatterns.or(DoctrineMetadataPattern.getFieldName(),DoctrineMetadataPattern.getFieldNameRelation())),psiElement -> new MyFieldNameGotoCompletionProvider(psiElement){
    @Nullable @Override protected String getElementText(    @NotNull PsiElement element){
      return GotoCompletionUtil.getXmlAttributeValue(element);
    }
  }
);
  registrar.register(DoctrineMetadataPattern.getYamlFieldName(),MyYamlFieldNameGotoCompletionProvider::new);
}
