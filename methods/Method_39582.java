/** 
 * Returns the abstract type stored at the given local variable index in the output frame.
 * @param localIndex the index of the local variable whose value must be returned.
 * @return the abstract type stored at the given local variable index in the output frame.
 */
private int getLocal(final int localIndex){
  if (outputLocals == null || localIndex >= outputLocals.length) {
    return LOCAL_KIND | localIndex;
  }
 else {
    int abstractType=outputLocals[localIndex];
    if (abstractType == 0) {
      abstractType=outputLocals[localIndex]=LOCAL_KIND | localIndex;
    }
    return abstractType;
  }
}
