/** 
 * Skips all keys after first definition, even if value is set later.
 */
public PropsEntries skipDuplicatesByPosition(){
  propsIterator.skipDuplicatesByPosition=true;
  propsIterator.skipDuplicatesByValue=false;
  return this;
}
