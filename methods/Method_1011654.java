public static void refactor(final ConsoleContext context,final Iterable<SNode> nodes,final _FunctionTypes._void_P1_E0<? super SNode> toExecuteWithEachNode){
  SearchResults sr=nodesToRefactoringResult(nodes);
  final SRepository projectRepo=context.getProject().getRepository();
  RefactoringAccessEx.getInstance().showRefactoringView(ProjectHelper.toIdeaProject(context.getProject()),new RefactoringViewAction(){
    public void performAction(    final RefactoringViewItem refactoringViewItem){
      projectRepo.getModelAccess().executeCommand(new Runnable(){
        public void run(){
          Iterable<SNode> includedNodes;
          if (refactoringViewItem instanceof RefactoringViewItemImpl) {
            List<SNodeReference> nodeRefs=as_bb8vid_a0a0a0a1a0a0a0a0a0a0b0a2a5(refactoringViewItem,RefactoringViewItemImpl.class).getUsagesView().getIncludedResultNodes();
            includedNodes=ListSequence.fromList(nodeRefs).select(new ISelector<SNodeReference,SNode>(){
              public SNode select(              SNodeReference it){
                return it.resolve(projectRepo);
              }
            }
);
          }
 else {
            includedNodes=nodes;
          }
          for (          SNode resultNode : Sequence.fromIterable(includedNodes)) {
            if (resultNode != null) {
              toExecuteWithEachNode.invoke(resultNode);
            }
          }
        }
      }
);
      refactoringViewItem.close();
      context.getOutputWindow().activate();
    }
  }
,sr,false,"refactor");
}
