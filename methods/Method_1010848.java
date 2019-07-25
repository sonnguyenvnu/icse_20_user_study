@Override public void init(final Project project){
  Module[] modules=ModuleManager.getInstance(project).getModules();
  assert modules.length == 1;
  final Module singleModule=modules[0];
  final jetbrains.mps.project.Project mpsProject=ProjectHelper.fromIdeaProject(project);
  if (mpsProject == null) {
    return;
  }
  final SRepository repository=mpsProject.getRepository();
  repository.getModelAccess().runWriteAction(() -> {
    SolutionDescriptor solutionDescriptor=makeDescriptor(singleModule);
    Solution solution=new SolutionIdea(singleModule,solutionDescriptor);
    if (repository.getModule(solution.getModuleId()) != null) {
      MessagesViewTool.log(project,MessageKind.ERROR,MPSBundle.message("facet.cannot.load.second.module",solutionDescriptor.getNamespace()));
      return;
    }
    ((SRepositoryExt)repository).registerModule(solution,mpsProject);
    mpsProject.addModule(solution);
    mySolution=solution;
  }
);
}
