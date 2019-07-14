@Override public void visitMethodInsn(final int opcode,final String owner,final String name,final String descriptor,final boolean isInterface){
  lastBytecodeOffset=code.length;
  Symbol methodrefSymbol=symbolTable.addConstantMethodref(owner,name,descriptor,isInterface);
  if (opcode == Opcodes.INVOKEINTERFACE) {
    code.put12(Opcodes.INVOKEINTERFACE,methodrefSymbol.index).put11(methodrefSymbol.getArgumentsAndReturnSizes() >> 2,0);
  }
 else {
    code.put12(opcode,methodrefSymbol.index);
  }
  if (currentBasicBlock != null) {
    if (compute == COMPUTE_ALL_FRAMES || compute == COMPUTE_INSERTED_FRAMES) {
      currentBasicBlock.frame.execute(opcode,0,methodrefSymbol,symbolTable);
    }
 else {
      int argumentsAndReturnSize=methodrefSymbol.getArgumentsAndReturnSizes();
      int stackSizeDelta=(argumentsAndReturnSize & 3) - (argumentsAndReturnSize >> 2);
      int size;
      if (opcode == Opcodes.INVOKESTATIC) {
        size=relativeStackSize + stackSizeDelta + 1;
      }
 else {
        size=relativeStackSize + stackSizeDelta;
      }
      if (size > maxRelativeStackSize) {
        maxRelativeStackSize=size;
      }
      relativeStackSize=size;
    }
  }
}
