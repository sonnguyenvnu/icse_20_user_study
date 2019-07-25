public void filter(final MethodNode methodNode,final IFilterContext context,final IFilterOutput output){
  for (  final TryCatchBlockNode tryCatch : methodNode.tryCatchBlocks) {
    if (tryCatch.type != null) {
      continue;
    }
    if (tryCatch.start == tryCatch.handler) {
      continue;
    }
    final AbstractInsnNode to=new Matcher(tryCatch.handler).match();
    if (to == null) {
      continue;
    }
    output.ignore(tryCatch.handler,to);
  }
}
