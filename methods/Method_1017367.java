@Override public void register(@NotNull GotoCompletionRegistrarParameter registrar){
  registrar.register(getTranslationTagValuePattern(),psiElement -> {
    TwigCompositeElement element=getTagOnTwigViewProvider(psiElement);
    if (element == null) {
      return null;
    }
    String domain=TwigUtil.getDomainFromTranslationTag(element);
    if (domain == null) {
      domain=TwigUtil.getTransDefaultDomainOnScope(element);
    }
    if (domain == null) {
      domain="messages";
    }
    return new MyTranslationGotoCompletionProvider(element,domain);
  }
);
}
