public void filter(final MethodNode methodNode,final IFilterContext context,final IFilterOutput output){
  for (  final TryCatchBlockNode tryCatchBlock : methodNode.tryCatchBlocks) {
    if (tryCatchBlock.type == null) {
      filter(output,methodNode.tryCatchBlocks,tryCatchBlock);
    }
  }
}
