/** 
 * Adds a mutation to set a metadata value. Passing  {@code null} as {@code name} or {@code value}isn't allowed.
 * @param name The name of the metadata value.
 * @param value The value to be set.
 * @return This instance, for convenience.
 */
public ContentMetadataMutations set(String name,byte[] value){
  return checkAndSet(name,Arrays.copyOf(value,value.length));
}
