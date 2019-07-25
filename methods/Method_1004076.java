public void filter(final MethodNode methodNode,final IFilterContext context,final IFilterOutput output){
  if (methodNode.tryCatchBlocks.isEmpty()) {
    return;
  }
  final Matcher matcher=new Matcher();
  for (  TryCatchBlockNode t : methodNode.tryCatchBlocks) {
    if ("java/lang/Throwable".equals(t.type)) {
      matcher.match(t.handler,output,true);
      matcher.match(t.handler,output,false);
    }
  }
}
