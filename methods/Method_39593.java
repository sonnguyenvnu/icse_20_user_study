/** 
 * Returns the bytecode offset corresponding to this label. This offset is computed from the start of the method's bytecode. <i>This method is intended for  {@link Attribute} sub classes, and isnormally not needed by class generators or adapters.</i>
 * @return the bytecode offset corresponding to this label.
 * @throws IllegalStateException if this label is not resolved yet.
 */
public int getOffset(){
  if ((flags & FLAG_RESOLVED) == 0) {
    throw new IllegalStateException("Label offset position has not been resolved yet");
  }
  return bytecodeOffset;
}
