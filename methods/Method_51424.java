/** 
 * Evaluates the names of the items against the allowable name prefixes. If one or more do not have valid prefixes then an exception will be thrown.
 * @param items             Items to check
 * @param legalNamePrefixes Legal name prefixes
 * @throws IllegalArgumentException if some items are not allowed
 */
private void checkValidDefaults(List<T> items,String[] legalNamePrefixes){
  if (legalNamePrefixes == null) {
    return;
  }
  Set<String> nameSet=new HashSet<>();
  for (  T item : items) {
    if (item == null) {
      continue;
    }
    nameSet.add(packageNameOf(item));
  }
  Set<String> notAllowed=new HashSet<>(nameSet);
  for (  String name : nameSet) {
    for (    String prefix : legalNamePrefixes) {
      if (name.startsWith(prefix)) {
        notAllowed.remove(name);
        break;
      }
    }
  }
  if (notAllowed.isEmpty()) {
    return;
  }
  throw new IllegalArgumentException("Invalid items: " + notAllowed);
}
