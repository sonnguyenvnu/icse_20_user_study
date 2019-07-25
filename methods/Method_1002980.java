public void invoke(@NotNull Project project,@NotNull PsiFile psiFile,@NotNull PsiClass psiClass){
  if (psiFile.isWritable()) {
    invoke(project,psiClass,processInnerClasses);
    finish(project,psiFile);
  }
}
