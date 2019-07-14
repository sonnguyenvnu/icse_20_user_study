/** 
 * Creates target set for injection. For now it creates <code>HashSet</code>, but custom implementation can change this setting.
 */
public Collection<T> createSet(final int length){
  return new HashSet<>(length);
}
