public void fork(){
  final InstructionFork instructionFork=new InstructionFork(current(),nextLinkRenderer(),getSkinParam());
  current().add(instructionFork);
  setNextLinkRendererInternal(LinkRendering.none());
  setCurrent(instructionFork);
}
