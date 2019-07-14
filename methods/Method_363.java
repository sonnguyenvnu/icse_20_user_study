public void visitVarInsn(final int opcode,final int var){
  if (var < 4 && opcode != Opcodes.RET) {
    int opt;
    if (opcode < Opcodes.ISTORE) {
      opt=26 + ((opcode - Opcodes.ILOAD) << 2) + var;
    }
 else {
      opt=59 + ((opcode - Opcodes.ISTORE) << 2) + var;
    }
    code.putByte(opt);
  }
 else   if (var >= 256) {
    code.putByte(196).put12(opcode,var);
  }
 else {
    code.put11(opcode,var);
  }
}
