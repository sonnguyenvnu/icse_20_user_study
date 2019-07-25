/** 
 * Set the entry at this index.
 * @param index the index
 * @param obj the object
 * @return the new immutable array
 */
public ImmutableArray<K> set(int index,K obj){
  K[] array=this.array.clone();
  array[index]=obj;
  return new ImmutableArray<>(array);
}
