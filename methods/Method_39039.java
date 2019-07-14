/** 
 * Returns all registered action runtime configurations. Returned list is a join of action paths with and without the macro.
 */
public List<ActionRuntime> getAllActionRuntimes(){
  return new ArrayList<>(runtimes.values());
}
