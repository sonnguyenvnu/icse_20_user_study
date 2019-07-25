/** 
 * Updates the provided changes into the source. If the key exists in the changes, it overrides the one in source unless both are Maps, in which case it recursively updated it.
 * @param source                 the original map to be updated
 * @param changes                the changes to update into updated
 * @param checkUpdatesAreUnequal should this method check if updates to the same key (that are not both maps) areunequal?  This is just a .equals check on the objects, but that can take some time on long strings.
 * @return true if the source map was modified
 */
public static boolean update(Map<String,Object> source,Map<String,Object> changes,boolean checkUpdatesAreUnequal){
  boolean modified=false;
  for (  Map.Entry<String,Object> changesEntry : changes.entrySet()) {
    if (!source.containsKey(changesEntry.getKey())) {
      source.put(changesEntry.getKey(),changesEntry.getValue());
      modified=true;
      continue;
    }
    Object old=source.get(changesEntry.getKey());
    if (old instanceof Map && changesEntry.getValue() instanceof Map) {
      modified|=update((Map<String,Object>)source.get(changesEntry.getKey()),(Map<String,Object>)changesEntry.getValue(),checkUpdatesAreUnequal && !modified);
      continue;
    }
    source.put(changesEntry.getKey(),changesEntry.getValue());
    if (modified) {
      continue;
    }
    if (!checkUpdatesAreUnequal) {
      modified=true;
      continue;
    }
    modified=!Objects.equals(old,changesEntry.getValue());
  }
  return modified;
}
