/** 
 * Returns the root (bottom / first) element of this history, or null if it's empty.
 * @param < K > the type of the key
 * @return the root (bottom) key
 */
@Nullable public <K>K root(){
  if (isEmpty()) {
    return null;
  }
  return (K)get(0);
}
