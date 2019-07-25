/** 
 * Returns a single weight from the samples contained in the sketch. Does not perform bounds checking on the input. If this is the first getter call, copies data arrays from the sketch.
 * @param i An index into the list of weights
 * @return The weight at array position <tt>i</tt>
 */
public double weights(final int i){
  loadArrays();
  return (sampleLists == null ? Double.NaN : sampleLists.weights[i]);
}
