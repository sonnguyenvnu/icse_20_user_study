/** 
 * Returns the index of the type argument that the given step is stepping into. This method should only be used for steps whose value is  {@link #TYPE_ARGUMENT}.
 * @param index an index between 0 and {@link #getLength()}, exclusive.
 * @return the index of the type argument that the given step is stepping into.
 */
public int getStepArgument(final int index){
  return typePathContainer[typePathOffset + 2 * index + 2];
}
