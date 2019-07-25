/** 
 * @return the runtimes of the used languages
 */
public Set<SModuleReference> invoke(@NotNull SModule module){
  Strategy strategy=isPackaged(module) ? new DeploymentStrategy() : new SourceStrategy();
  return strategy.findRuntimes(module);
}
