/** 
 * Get a list of all registered instances.
 * @return List of all instances.
 */
public Flux<Instance> getInstances(){
  return repository.findAll();
}
