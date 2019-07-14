/** 
 * Returns the actual array being used to store the data. For advanced users, this is the fastest way to access a large list. Suitable for iterating with a for() loop, but modifying the list will have terrible consequences.
 */
public int[] values(){
  crop();
  return data;
}
