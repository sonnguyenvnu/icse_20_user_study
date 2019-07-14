/** 
 * Adds a mutation to remove a metadata value.
 * @param name The name of the metadata value.
 * @return This instance, for convenience.
 */
public ContentMetadataMutations remove(String name){
  removedValues.add(name);
  editedValues.remove(name);
  return this;
}
