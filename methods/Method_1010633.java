public Project load(){
  final ModuleRepositoryFacade rf=new ModuleRepositoryFacade(myProject);
  myProject.getModelAccess().runWriteAction(new Runnable(){
    public void run(){
      for (      ModulesMiner.ModuleHandle moduleHandle : myHandlesToLoad) {
        SModule module=rf.instantiateModule(moduleHandle,myProject);
        if (module instanceof Generator) {
          continue;
        }
        myProject.addModule(module);
      }
    }
  }
);
  return myProject;
}
