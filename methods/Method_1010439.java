/** 
 * @param modules have to be a subset of {@link #getModules()}
 * @return an instance restricted to set of speficied modules
 */
public ModulesContainer restricted(@NotNull Set<SModule> modules){
  if (!myModules.containsAll(modules)) {
    throw new IllegalArgumentException("Attempt to restrict a container to modules that are not part of it");
  }
  return new ModulesContainer(modules,myDependencies,myModuleSources);
}
