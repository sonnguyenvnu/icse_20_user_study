public void resolve(Collection<TwigTypeContainer> targets,Collection<TwigTypeContainer> previousElement,String typeName,Collection<List<TwigTypeContainer>> previousElements,@Nullable Collection<PsiVariable> psiVariables){
  if (!"vars".equals(typeName) || previousElements.size() == 0) {
    return;
  }
  List<TwigTypeContainer> lastTwigTypeContainer=null;
  for (Iterator collectionItr=previousElements.iterator(); collectionItr.hasNext(); ) {
    lastTwigTypeContainer=(List<TwigTypeContainer>)collectionItr.next();
  }
  for (  TwigTypeContainer twigTypeContainer : lastTwigTypeContainer) {
    if (twigTypeContainer.getPhpNamedElement() instanceof PhpClass) {
      if (PhpElementsUtil.isInstanceOf((PhpClass)twigTypeContainer.getPhpNamedElement(),"\\Symfony\\Component\\Form\\FormView")) {
        attachVars(twigTypeContainer.getPhpNamedElement().getProject(),targets);
      }
    }
  }
}
