/** 
 * Replaces the abstract type stored at the given local variable index in the output frame.
 * @param localIndex the index of the output frame local variable that must be set.
 * @param abstractType the value that must be set.
 */
private void setLocal(final int localIndex,final int abstractType){
  if (outputLocals == null) {
    outputLocals=new int[10];
  }
  int outputLocalsLength=outputLocals.length;
  if (localIndex >= outputLocalsLength) {
    int[] newOutputLocals=new int[Math.max(localIndex + 1,2 * outputLocalsLength)];
    System.arraycopy(outputLocals,0,newOutputLocals,0,outputLocalsLength);
    outputLocals=newOutputLocals;
  }
  outputLocals[localIndex]=abstractType;
}
