public List<String> validate(){
  Language lang=myProjectLanguageTreeNode.getModule();
  if (lang.getRepository() == null) {
    return Collections.emptyList();
  }
  List<String> errors=new ArrayList<>();
  for (  SModelReference accessory : lang.getModuleDescriptor().getAccessoryModels()) {
    SModel accModel=accessory.resolve(lang.getRepository());
    if (accModel == null) {
      continue;
    }
    if (!VisibilityUtil.isVisible(lang,accModel)) {
      errors.add("Can't find accessory " + accessory.getName());
    }
  }
  return errors;
}
