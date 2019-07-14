/** 
 * Returns set of all bean names. The returned set is a safe snapshot of all bean names.
 */
public Set<String> beanNames(){
  return new HashSet<>(beans.keySet());
}
