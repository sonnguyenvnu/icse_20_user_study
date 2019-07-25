@Override public void accept(final MethodNode methodNode,final MethodVisitor methodVisitor){
  methodVisitor.visitCode();
  for (  final TryCatchBlockNode n : methodNode.tryCatchBlocks) {
    n.accept(methodVisitor);
  }
  currentNode=methodNode.instructions.getFirst();
  while (currentNode != null) {
    currentNode.accept(methodVisitor);
    currentNode=currentNode.getNext();
  }
  methodVisitor.visitEnd();
}
