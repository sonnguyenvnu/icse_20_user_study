/** 
 * Simulates the effect of the instruction at the given offset, by making appropriate calls on the given frame.
 * @param offset {@code offset >= 0;} offset of the instruction to simulate
 * @param frame {@code non-null;} frame to operate on
 * @return the length of the instruction, in bytes
 */
public int simulate(int offset,Frame frame){
  visitor.setFrame(frame);
  return code.parseInstruction(offset,visitor);
}
