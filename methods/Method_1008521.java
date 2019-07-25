/** 
 * Return the set of concrete types that have a mapping. NOTE: this does not return the default mapping.
 */
public Collection<String> types(){
  final Set<String> types=new HashSet<>(mappers.keySet());
  types.remove(DEFAULT_MAPPING);
  return Collections.unmodifiableSet(types);
}
