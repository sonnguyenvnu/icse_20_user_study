@Override public EvaluationWithContextContainer copy(final boolean isInWatch,_FunctionTypes._void_P1_E0<? super IEvaluationContainer> onNodeSetUp){
  final SNodeReference reference=myNode;
  return new EvaluationWithContextContainer(myProject,myDebugSession,myContainerModule,ListSequence.fromList(new ArrayList<SNodeReference>()),isInWatch,onNodeSetUp){
    @Override protected SNode createEvaluatorNode(){
      SNode newEvaluator=(SNode)CopyUtil.copyAndPreserveId(reference.resolve(myDebuggerRepository),true);
      SPropertyOperations.set(newEvaluator,MetaAdapterFactory.getProperty(0x7da4580f9d754603L,0x816251a896d78375L,0x53c5060c6b18d925L,0x53c5060c6b19c79bL,"isShowContext"),isInWatch);
      return newEvaluator;
    }
  }
;
}
