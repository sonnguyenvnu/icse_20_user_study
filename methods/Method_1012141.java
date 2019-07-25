private <T extends MPSTreeNode & TreeElement>void schedule(final T node,final TreeNodeVisitor visitor){
  myExecutor.execute(new ModelReadRunnable(myProjectRepository.getModelAccess(),() -> {
    boolean disposed=node.getTree() == null;
    if (disposed) {
      return;
    }
    node.accept(visitor);
  }
));
}
