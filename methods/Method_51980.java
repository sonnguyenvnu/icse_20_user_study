/** 
 * Collects the attribute accesses by method into a map.
 */
@SuppressWarnings("unchecked") public Map<String,Set<String>> start(){
  return (Map<String,Set<String>>)this.visit(exploredClass,new HashMap<String,Set<String>>());
}
