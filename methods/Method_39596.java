/** 
 * Sets the bytecode offset of this label to the given value and resolves the forward references to this label, if any. This method must be called when this label is added to the bytecode of the method, i.e. when its bytecode offset becomes known. This method fills in the blanks that where left in the bytecode by each forward reference previously added to this label.
 * @param code the bytecode of the method.
 * @param bytecodeOffset the bytecode offset of this label.
 * @return {@literal true} if a blank that was left for this label was too small to store theoffset. In such a case the corresponding jump instruction is replaced with an equivalent ASM specific instruction using an unsigned two bytes offset. These ASM specific instructions are later replaced with standard bytecode instructions with wider offsets (4 bytes instead of 2), in ClassReader.
 */
final boolean resolve(final byte[] code,final int bytecodeOffset){
  this.flags|=FLAG_RESOLVED;
  this.bytecodeOffset=bytecodeOffset;
  if (forwardReferences == null) {
    return false;
  }
  boolean hasAsmInstructions=false;
  for (int i=forwardReferences[0]; i > 0; i-=2) {
    final int sourceInsnBytecodeOffset=forwardReferences[i - 1];
    final int reference=forwardReferences[i];
    final int relativeOffset=bytecodeOffset - sourceInsnBytecodeOffset;
    int handle=reference & FORWARD_REFERENCE_HANDLE_MASK;
    if ((reference & FORWARD_REFERENCE_TYPE_MASK) == FORWARD_REFERENCE_TYPE_SHORT) {
      if (relativeOffset < Short.MIN_VALUE || relativeOffset > Short.MAX_VALUE) {
        int opcode=code[sourceInsnBytecodeOffset] & 0xFF;
        if (opcode < Opcodes.IFNULL) {
          code[sourceInsnBytecodeOffset]=(byte)(opcode + Constants.ASM_OPCODE_DELTA);
        }
 else {
          code[sourceInsnBytecodeOffset]=(byte)(opcode + Constants.ASM_IFNULL_OPCODE_DELTA);
        }
        hasAsmInstructions=true;
      }
      code[handle++]=(byte)(relativeOffset >>> 8);
      code[handle]=(byte)relativeOffset;
    }
 else {
      code[handle++]=(byte)(relativeOffset >>> 24);
      code[handle++]=(byte)(relativeOffset >>> 16);
      code[handle++]=(byte)(relativeOffset >>> 8);
      code[handle]=(byte)relativeOffset;
    }
  }
  return hasAsmInstructions;
}
