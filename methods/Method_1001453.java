public CommandExecutionResult endwhile(Display out){
  if (current() instanceof InstructionWhile) {
    ((InstructionWhile)current()).endwhile(nextLinkRenderer(),out);
    setNextLinkRendererInternal(LinkRendering.none());
    setCurrent(((InstructionWhile)current()).getParent());
    return CommandExecutionResult.ok();
  }
  return CommandExecutionResult.error("Cannot find while");
}
