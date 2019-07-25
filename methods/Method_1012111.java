@Override public void init(){
  ClassLikeMenuAdjustment_AppPluginPart.this.myLangRegistry=getPlatform().findComponent(LanguageRegistry.class);
  ClassLikeMenuAdjustment_AppPluginPart.this.myLangRegistry.addRegistryListener(ClassLikeMenuAdjustment_AppPluginPart.this.myReloadListener);
  ClassLikeMenuAdjustment_AppPluginPart.this.myDeploymentRepo=getPlatform().findComponent(MPSModuleRepository.class);
  CreateRootFilterEP.getInstance().addFilter(ClassLikeMenuAdjustment_AppPluginPart.this.myCondition);
}
