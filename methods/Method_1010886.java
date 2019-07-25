private boolean filter(PsiElement elem){
  if (elem == null || elem instanceof PsiWhiteSpace) {
    return false;
  }
  if (elem instanceof PsiJavaFile || elem instanceof PsiDirectory) {
    return true;
  }
  PsiElement e=elem;
  do {
    if (interesting(e)) {
      return true;
    }
    if (notInteresting(e)) {
      return false;
    }
    e=e.getParent();
  }
 while (e != null);
  return false;
}
