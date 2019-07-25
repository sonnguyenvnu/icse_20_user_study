@Override public PsiElement copy(){
  return new LightIdentifier(getManager(),getText());
}
