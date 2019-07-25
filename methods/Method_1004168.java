@Override protected boolean handle(Set<? extends TypeElement> annotations){
  boolean modulesAndClassesCollected=collectModulesAndClasses(annotations);
  boolean eitherComponentsCollected=collectEitherComponents(annotations);
  logger.w("state: %s, \nallModules: %s\nallInjected: %s\nallComponent: %s ",state,allModules,allInjected,allEitherComponents);
switch (state) {
case INITIAL:
    if (modulesAndClassesCollected) {
      generateHubInterface(allModules,allInjected,processingEnv,roundEnvironment,utils);
      state=State.HUB_INTERFACE_GENERATED;
    }
  return false;
case HUB_INTERFACE_GENERATED:
generatePackagedInjectors(allModules,allInjected,processingEnv,roundEnvironment,utils);
state=State.PACKGED_INJECTOR_GENERATED;
return false;
case PACKGED_INJECTOR_GENERATED:
if (eitherComponentsCollected) {
addSiblingsInSamePackage(allEitherComponents);
componentToParentMap=collectComponentToParentMap(allEitherComponents);
generateSubcomponentParentInterfaces();
state=State.SUBCOMPONENT_PARENT_INTERFACE_GENERAED;
}
return false;
case SUBCOMPONENT_PARENT_INTERFACE_GENERAED:
if (eitherComponentsCollected) {
handleHub();
state=State.HUB_INJECTOR_GENERATED;
}
return false;
case HUB_INJECTOR_GENERATED:
return true;
}
return false;
}
