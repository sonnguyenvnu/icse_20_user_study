/** 
 * Create the injector and call any LifecycleListeners
 * @param args - Runtime parameter (from main) injectable as {@literal @}Arguments String[]
 * @return the LifecycleInjector for this run
 */
private LifecycleInjector run(Module externalModule,final String[] args){
  return InjectorBuilder.fromModules(modules).combineWith(externalModule).map(new ModuleTransformer(){
    @Override public Module transform(    Module module){
      List<Module> modulesToTransform=Collections.singletonList(module);
      for (      ModuleListTransformer transformer : transformers) {
        modulesToTransform=transformer.transform(Collections.unmodifiableList(modulesToTransform));
      }
      return Modules.combine(modulesToTransform);
    }
  }
).overrideWith(overrideModules).createInjector(new LifecycleInjectorCreator().withArguments(args).withFeatures(featureOverrides).withProfiles(profiles));
}
