/** 
 * Returns the root (first) element of this history, or null if the history is empty.
 * @throws IllegalStateException if the NavigationCore history doesn't contain any elements yet.
 * @param < K > the type of the key
 * @return the root (first) key
 */
@NonNull public <K>K root(){
  if (stack.isEmpty()) {
    throw new IllegalStateException("Cannot obtain elements from an uninitialized NavigationCore.");
  }
  return (K)stack.get(0);
}
