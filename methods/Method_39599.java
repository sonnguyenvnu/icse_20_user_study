/** 
 * Visits a method instruction. A method instruction is an instruction that invokes a method.
 * @param opcode the opcode of the type instruction to be visited. This opcode is eitherINVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
 * @param owner the internal name of the method's owner class (see {@link Type#getInternalName()}).
 * @param name the method's name.
 * @param descriptor the method's descriptor (see {@link Type}).
 * @param isInterface if the method's owner class is an interface.
 */
public void visitMethodInsn(final int opcode,final String owner,final String name,final String descriptor,final boolean isInterface){
  if (api < Opcodes.ASM5 && (opcode & Opcodes.SOURCE_DEPRECATED) == 0) {
    if (isInterface != (opcode == Opcodes.INVOKEINTERFACE)) {
      throw new UnsupportedOperationException("INVOKESPECIAL/STATIC on interfaces requires ASM5");
    }
    visitMethodInsn(opcode,owner,name,descriptor);
    return;
  }
  if (mv != null) {
    mv.visitMethodInsn(opcode & ~Opcodes.SOURCE_MASK,owner,name,descriptor,isInterface);
  }
}
