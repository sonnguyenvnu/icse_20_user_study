public void start(){
  manageSwimlaneStrategy();
  current().add(new InstructionStart(swinlanes.getCurrentSwimlane(),nextLinkRenderer()));
  setNextLinkRendererInternal(LinkRendering.none());
}
