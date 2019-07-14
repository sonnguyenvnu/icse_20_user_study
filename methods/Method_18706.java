@NotNull @Override public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root,@NotNull Document document,boolean quick){
  FoldingGroup group=FoldingGroup.newGroup(FOLDING_GROUP_NAME);
  final Map<String,PsiExpression> defaultProps=PsiTreeUtil.findChildrenOfType(root,PsiField.class).stream().filter(LithoPluginUtils::isPropDefault).filter(field -> field.getInitializer() != null).collect(Collectors.toMap(PsiField::getName,PsiField::getInitializer));
  if (defaultProps.isEmpty()) {
    return FoldingDescriptor.EMPTY;
  }
  return PsiTreeUtil.findChildrenOfType(root,PsiParameter.class).stream().filter(LithoPluginUtils::isProp).map(parameter -> {
    String name=parameter.getName();
    if (name == null) {
      return null;
    }
    PsiExpression nameExpression=defaultProps.get(name);
    if (nameExpression == null) {
      return null;
    }
    PsiIdentifier nameIdentifier=parameter.getNameIdentifier();
    if (nameIdentifier == null) {
      return null;
    }
    return new FoldingDescriptor(nameIdentifier.getNode(),nameIdentifier.getTextRange(),group){
      @Override public String getPlaceholderText(){
        return name + ": " + nameExpression.getText();
      }
    }
;
  }
).filter(Objects::nonNull).toArray(FoldingDescriptor[]::new);
}
