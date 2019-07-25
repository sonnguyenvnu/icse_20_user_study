/** 
 * Record module that keeps transient models for the given task. Module originates from  {@link #createModule(String)}. This association is utilized by  {@link #getModule(GeneratorTask)}. Silently overwrites existing association, if any.
 * @param task transformation activity
 * @param transientModule module to keep transient models at
 */
public void associate(@NotNull GeneratorTask task,@NotNull TransientModelsModule transientModule){
  myModuleMap.put(task,transientModule);
}
