/** 
 * Returns the last element in the list, or null if the history is empty.
 * @throws IllegalStateException if the NavigationCore history doesn't contain any elements yet.
 * @param < K > the type of the key
 * @return the top key
 */
@NonNull public <K>K top(){
  if (stack.isEmpty()) {
    throw new IllegalStateException("Cannot obtain elements from an uninitialized NavigationCore.");
  }
  return (K)stack.get(stack.size() - 1);
}
