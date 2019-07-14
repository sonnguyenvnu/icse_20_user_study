/** 
 * Throws an exception if called while we are in the middle of a mutation operation (addAll or replaceAll).
 */
private void throwIfInMutationOperation(){
  if (mOldData != null) {
    throw new IllegalStateException("Data cannot be mutated in the middle of a batch " + "update operation such as addAll or replaceAll.");
  }
}
