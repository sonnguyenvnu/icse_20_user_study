/** 
 * Skips duplicate keys (defined in different profiles) which value is not used for setting current key value.
 */
public PropsEntries skipDuplicatesByValue(){
  propsIterator.skipDuplicatesByValue=true;
  propsIterator.skipDuplicatesByPosition=false;
  return this;
}
