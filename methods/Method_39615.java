/** 
 * Starts the visit of a new stack map frame, stored in  {@link #currentFrame}.
 * @param offset the bytecode offset of the instruction to which the frame corresponds.
 * @param numLocal the number of local variables in the frame.
 * @param numStack the number of stack elements in the frame.
 * @return the index of the next element to be written in this frame.
 */
int visitFrameStart(final int offset,final int numLocal,final int numStack){
  int frameLength=3 + numLocal + numStack;
  if (currentFrame == null || currentFrame.length < frameLength) {
    currentFrame=new int[frameLength];
  }
  currentFrame[0]=offset;
  currentFrame[1]=numLocal;
  currentFrame[2]=numStack;
  return 3;
}
