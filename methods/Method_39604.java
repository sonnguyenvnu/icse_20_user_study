@Override public void visitTypeInsn(final int opcode,final String type){
  lastBytecodeOffset=code.length;
  Symbol typeSymbol=symbolTable.addConstantClass(type);
  code.put12(opcode,typeSymbol.index);
  if (currentBasicBlock != null) {
    if (compute == COMPUTE_ALL_FRAMES || compute == COMPUTE_INSERTED_FRAMES) {
      currentBasicBlock.frame.execute(opcode,lastBytecodeOffset,typeSymbol,symbolTable);
    }
 else     if (opcode == Opcodes.NEW) {
      int size=relativeStackSize + 1;
      if (size > maxRelativeStackSize) {
        maxRelativeStackSize=size;
      }
      relativeStackSize=size;
    }
  }
}
