@Override public void visitLabel(final Label label){
  hasAsmInstructions|=label.resolve(code.data,code.length);
  if ((label.flags & Label.FLAG_DEBUG_ONLY) != 0) {
    return;
  }
  if (compute == COMPUTE_ALL_FRAMES) {
    if (currentBasicBlock != null) {
      if (label.bytecodeOffset == currentBasicBlock.bytecodeOffset) {
        currentBasicBlock.flags|=(label.flags & Label.FLAG_JUMP_TARGET);
        label.frame=currentBasicBlock.frame;
        return;
      }
      addSuccessorToCurrentBasicBlock(Edge.JUMP,label);
    }
    if (lastBasicBlock != null) {
      if (label.bytecodeOffset == lastBasicBlock.bytecodeOffset) {
        lastBasicBlock.flags|=(label.flags & Label.FLAG_JUMP_TARGET);
        label.frame=lastBasicBlock.frame;
        currentBasicBlock=lastBasicBlock;
        return;
      }
      lastBasicBlock.nextBasicBlock=label;
    }
    lastBasicBlock=label;
    currentBasicBlock=label;
    label.frame=new Frame(label);
  }
 else   if (compute == COMPUTE_INSERTED_FRAMES) {
    if (currentBasicBlock == null) {
      currentBasicBlock=label;
    }
 else {
      currentBasicBlock.frame.owner=label;
    }
  }
 else   if (compute == COMPUTE_MAX_STACK_AND_LOCAL) {
    if (currentBasicBlock != null) {
      currentBasicBlock.outputStackMax=(short)maxRelativeStackSize;
      addSuccessorToCurrentBasicBlock(relativeStackSize,label);
    }
    currentBasicBlock=label;
    relativeStackSize=0;
    maxRelativeStackSize=0;
    if (lastBasicBlock != null) {
      lastBasicBlock.nextBasicBlock=label;
    }
    lastBasicBlock=label;
  }
 else   if (compute == COMPUTE_MAX_STACK_AND_LOCAL_FROM_FRAMES && currentBasicBlock == null) {
    currentBasicBlock=label;
  }
}
