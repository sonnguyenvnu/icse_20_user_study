@Nullable public static Collection<ServiceTagInterface> create(@NotNull String serviceId,@NotNull PsiElement psiElement){
  if (psiElement instanceof YAMLKeyValue) {
    return create((YAMLKeyValue)psiElement);
  }
 else   if (psiElement instanceof XmlTag) {
    return create(serviceId,(XmlTag)psiElement);
  }
  return null;
}
