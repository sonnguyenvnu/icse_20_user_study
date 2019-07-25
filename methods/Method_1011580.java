public void dispose(){
  myProject.getModelAccess().runWriteAction(new Runnable(){
    public void run(){
      SRepository projectRepo=myProject.getRepository();
      ((SRepositoryExt)projectRepo).unregisterModule(myModule,myProject);
      FileUtil.delete(myModule.getBaseDirectory());
    }
  }
);
}
