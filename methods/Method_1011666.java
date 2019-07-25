public void execute(final MPSProject project,List<SNode> nodesToMove){
  final List<SNode> conceptsToMove=Sequence.fromIterable(SNodeOperations.ofConcept(nodesToMove,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"))).toListSequence();
  ListSequence.fromList(conceptsToMove).visitAll(new IVisitor<SNode>(){
    public void visit(    SNode it){
      checkDeployed(project,it);
    }
  }
);
  final Wrappers._boolean hasGenerator=new Wrappers._boolean(false);
  project.getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      hasGenerator.value=ListSequence.fromList(conceptsToMove).any(new IWhereFilter<SNode>(){
        public boolean accept(        SNode node){
          return ListSequence.fromList(AbstractConceptDeclaration__BehaviorDescriptor.findGeneratorFragments_id5zMz2aJEI4B.invoke(node)).isNotEmpty();
        }
      }
);
    }
  }
);
  if (hasGenerator.value) {
    Messages.showWarningDialog(project.getProject(),"Generator fragments will not be moved.",NAME);
  }
  final Wrappers._T<List<SModelReference>> structureModels=new Wrappers._T<List<SModelReference>>();
  project.getRepository().getModelAccess().runReadAction(new _Adapters._return_P0_E0_to_Runnable_adapter(new _FunctionTypes._return_P0_E0<List<SModelReference>>(){
    public List<SModelReference> invoke(){
      Iterable<SModule> modules=project.getProjectModules();
      return structureModels.value=Sequence.fromIterable(modules).ofType(Language.class).select(new ISelector<Language,SModelReference>(){
        public SModelReference select(        Language it){
          return check_u6ijv2_a0a0a0a0a1a0i0f(it.getStructureModelDescriptor());
        }
      }
).where(new IWhereFilter<SModelReference>(){
        public boolean accept(        SModelReference it){
          return it != null;
        }
      }
).toListSequence();
    }
  }
));
  final SModelReference targetModelRef=CommonChoosers.showModelChooser(project,NAME,structureModels.value);
  if (targetModelRef == null) {
    return;
  }
  final Wrappers._T<SModel> targetModel=new Wrappers._T<SModel>();
  project.getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      targetModel.value=targetModelRef.resolve(project.getRepository());
    }
  }
);
  MoveNodesUtil.moveTo(project,getName(),MapSequence.<MoveNodesUtil.NodeProcessor,List<SNode>>fromMapAndKeysArray(new HashMap<MoveNodesUtil.NodeProcessor,List<SNode>>(),new MoveNodesUtil.NodeCreatingProcessor(new NodeLocation.NodeLocationRoot(targetModel.value),project)).withValues(nodesToMove));
}
