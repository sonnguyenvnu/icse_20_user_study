public void filter(final MethodNode methodNode,final IFilterContext context,final IFilterOutput output){
  if (methodNode.tryCatchBlocks.isEmpty()) {
    return;
  }
  final Matcher matcher=new Matcher(output);
  for (  final TryCatchBlockNode t : methodNode.tryCatchBlocks) {
    if (t.type == null) {
      matcher.start(t.handler);
      if (!matcher.matchEcj()) {
        matcher.start(t.handler);
        matcher.matchEcjNoFlowOut();
      }
    }
  }
}
