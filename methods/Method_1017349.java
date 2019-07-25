@Override public void navigate(boolean requestFocus){
  final Navigatable descriptor=PsiNavigationSupport.getInstance().getDescriptor(this.psiElement);
  if (descriptor != null) {
    descriptor.navigate(requestFocus);
  }
}
