public void unregister(){
  SRepository repository=myProject.getRepository();
  repository.getModelAccess().runReadAction(() -> repository.removeRepositoryListener(myModuleChangesListener));
}
