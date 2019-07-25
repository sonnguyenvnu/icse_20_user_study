/** 
 * Simulates the effect of executing the given basic block. This modifies the passed-in frame to represent the end result.
 * @param bb {@code non-null;} the basic block
 * @param frame {@code non-null;} frame to operate on
 */
public void simulate(ByteBlock bb,Frame frame){
  int end=bb.getEnd();
  visitor.setFrame(frame);
  try {
    for (int off=bb.getStart(); off < end; ) {
      int length=code.parseInstruction(off,visitor);
      visitor.setPreviousOffset(off);
      off+=length;
    }
  }
 catch (  SimException ex) {
    frame.annotate(ex);
    throw ex;
  }
}
