/** 
 * Returns the value of the given step of this path.
 * @param index an index between 0 and {@link #getLength()}, exclusive.
 * @return one of {@link #ARRAY_ELEMENT},  {@link #INNER_TYPE},  {@link #WILDCARD_BOUND}, or  {@link #TYPE_ARGUMENT}.
 */
public int getStep(final int index){
  return typePathContainer[typePathOffset + 2 * index + 1];
}
