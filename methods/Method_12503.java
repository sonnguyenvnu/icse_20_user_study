/** 
 * Get a list of all registered application instances.
 * @param name the name to search for.
 * @return List of instances for the given application
 */
public Flux<Instance> getInstances(String name){
  return repository.findByName(name);
}
