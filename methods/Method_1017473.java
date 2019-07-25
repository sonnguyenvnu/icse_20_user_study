/** 
 * Returns the last element in the list, or null if the history is empty.
 * @param < K > the type of the key
 * @return the top key
 */
@Nullable public <K>K top(){
  if (this.isEmpty()) {
    return null;
  }
  return (K)this.get(this.size() - 1);
}
