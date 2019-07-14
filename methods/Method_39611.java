@Override public void visitMaxs(final int maxStack,final int maxLocals){
  if (compute == COMPUTE_ALL_FRAMES) {
    computeAllFrames();
  }
 else   if (compute == COMPUTE_MAX_STACK_AND_LOCAL) {
    computeMaxStackAndLocal();
  }
 else   if (compute == COMPUTE_MAX_STACK_AND_LOCAL_FROM_FRAMES) {
    this.maxStack=maxRelativeStackSize;
  }
 else {
    this.maxStack=maxStack;
    this.maxLocals=maxLocals;
  }
}
