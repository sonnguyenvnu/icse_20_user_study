public void registerKey(Component component){
  checkIsDuplicateKey(component);
  mKnownGlobalKeys.add(component.getGlobalKey());
}
