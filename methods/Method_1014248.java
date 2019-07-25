/** 
 * Deletes all presets from the storage.
 */
public void clear(){
  if (storage instanceof DeletableStorage) {
    ((DeletableStorage<ContentItem>)storage).delete();
  }
 else {
    Collection<@NonNull String> keys=storage.getKeys();
    keys.forEach(key -> storage.remove(key));
  }
}
