/** 
 * Returns whether this cache notifies a writer when an entry is modified. 
 */
protected boolean hasWriter(){
  return (writer != CacheWriter.disabledWriter());
}
