@Override public void fill(){
  getSecurityDefinitions().forEach(field -> {
    final PsiFile containingFile=completionHelper.getPsiFile().getContainingFile();
    final List<? extends PsiNamedElement> security=new PathFinder().findNamedChildren("$.security",containingFile);
    final List<String> existingNames=extractNames(security);
    if (!existingNames.contains(field.getName())) {
      addUnique(field);
    }
  }
);
}
