/** 
 * Retrieves the front of this queue in a given array (optional operation). <p>The <em>front</em> of an indirect queue is the set of indices whose associated elements in the reference array are equal to the element associated to the  {@linkplain #first() first index}. These indices can be always obtain by dequeueing, but this method should retrieve efficiently such indices in the given array without modifying the state of this queue. <p>This default implementation just throws an  {@link UnsupportedOperationException}.
 * @param a an array large enough to hold the front (e.g., at least long as the reference array).
 * @return the number of elements actually written (starting from the first position of {@code a}).
 */
default int front(final int[] a){
  throw new UnsupportedOperationException();
}
