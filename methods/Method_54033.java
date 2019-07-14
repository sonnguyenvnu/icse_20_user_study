/** 
 * Returns a  {@link DoubleBuffer} view of the data pointed to by the {@code mWeights} field. 
 */
@NativeType("double *") public DoubleBuffer mWeights(){
  return nmWeights(address());
}
