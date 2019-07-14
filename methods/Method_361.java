/** 
 * Resolves all forward references to this label. This method must be called when this label is added to the bytecode of the method, i.e. when its position becomes known. This method fills in the blanks that where left in the bytecode by each forward reference previously added to this label.
 * @param owner the code writer that calls this method.
 * @param position the position of this label in the bytecode.
 * @param data the bytecode of the method.
 * @return <tt>true</tt> if a blank that was left for this label was to small to store the offset. In such a casethe corresponding jump instruction is replaced with a pseudo instruction (using unused opcodes) using an unsigned two bytes offset. These pseudo instructions will need to be replaced with true instructions with wider offsets (4 bytes instead of 2). This is done in  {@link MethodWriter#resizeInstructions}.
 * @throws IllegalArgumentException if this label has already been resolved, or if it has not been created by thegiven code writer.
 */
void resolve(final MethodWriter owner,final int position,final byte[] data){
  this.status|=2;
  this.position=position;
  int i=0;
  while (i < referenceCount) {
    int source=srcAndRefPositions[i++];
    int reference=srcAndRefPositions[i++];
    int offset=position - source;
    data[reference++]=(byte)(offset >>> 8);
    data[reference]=(byte)offset;
  }
}
