/** 
 * A helper method for populating  {@link #startIndex} and {@link #endIndex} given thecurrent and target characters for the animation.
 */
private void setCharacterIndices(){
  startIndex=characterIndicesMap.containsKey(currentChar) ? characterIndicesMap.get(currentChar) : UNKNOWN_START_INDEX;
  endIndex=characterIndicesMap.containsKey(targetChar) ? characterIndicesMap.get(targetChar) : UNKNOWN_END_INDEX;
}
