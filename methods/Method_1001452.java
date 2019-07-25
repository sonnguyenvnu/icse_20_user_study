public void split(){
  final InstructionSplit instructionSplit=new InstructionSplit(current(),nextLinkRenderer(),swinlanes.getCurrentSwimlane());
  setNextLinkRendererInternal(LinkRendering.none());
  current().add(instructionSplit);
  setCurrent(instructionSplit);
}
