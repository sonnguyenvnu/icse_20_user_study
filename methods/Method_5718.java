/** 
 * Returns whether this result is equivalent to  {@code other} for the renderer at the given index.The results are equivalent if they have equal renderersEnabled array, track selections, and configurations for the renderer.
 * @param other The other {@link TrackSelectorResult}. May be null, in which case  {@code false}will be returned.
 * @param index The renderer index to check for equivalence.
 * @return Whether this result is equivalent to {@code other} for the renderer at the specifiedindex.
 */
public boolean isEquivalent(@Nullable TrackSelectorResult other,int index){
  if (other == null) {
    return false;
  }
  return Util.areEqual(rendererConfigurations[index],other.rendererConfigurations[index]) && Util.areEqual(selections.get(index),other.selections.get(index));
}
