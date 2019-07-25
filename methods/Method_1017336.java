@Nullable @Override public PsiElement resolve(){
  Collection<PsiElement> defaultOptionTargets=FormOptionsUtil.getDefaultOptionTargets(element,formType);
  if (defaultOptionTargets.size() > 0) {
    return defaultOptionTargets.iterator().next();
  }
  return null;
}
