@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(PlatformPatterns.or(YamlElementPatternHelper.getTaggedServicePattern(),XmlPatterns.psiElement().withParent(XmlHelper.getTypeTaggedTagAttribute())),new MyTagGotoCompletionContributor());
}
