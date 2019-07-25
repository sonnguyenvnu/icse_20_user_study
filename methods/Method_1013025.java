@Override public void fill(){
  getSecurityDefinitions().forEach(field -> completionHelper.getParentByName("security").ifPresent(securityParent -> {
    final List<PsiElement> security=completionHelper.getChildrenOfArrayObject(securityParent);
    final List<String> existingNames=extractNames(security);
    if (!existingNames.contains(field.getName())) {
      addUnique(field);
    }
  }
));
}
