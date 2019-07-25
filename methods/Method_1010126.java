/** 
 * Register facility responsible for transient model handling, <em>mandatory</em>.
 * @param transientModelsProvider transient model facility
 * @return <code>this</code> for convenience
 */
public GenerationFacade transients(@NotNull TransientModelsProvider transientModelsProvider){
  myTransientModelsProvider=transientModelsProvider;
  return this;
}
