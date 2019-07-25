private synchronized void init(){
  final SRepository repository=myDebugSession.getProject().getRepository();
  repository.getModelAccess().runWriteAction(new Runnable(){
    public void run(){
      EvaluationModule module=new EvaluationModule(ProjectHelper.fromIdeaProject(myDebugSession.getIdeaProject()).getPlatform());
      ((SRepositoryExt)repository).registerModule(module,myDebugSession.getProject());
      myContainerModuleRef=module.getModuleReference();
    }
  }
);
}
