protected void reload(final MPSCompilationResult mpsCompilationResult){
  if (mpsCompilationResult.isReloadingNeeded()) {
    myEnv.getPlatform().findComponent(MPSModuleRepository.class).getModelAccess().runWriteAction(new Runnable(){
      public void run(){
        ClassLoaderManager.getInstance().reloadModules(mpsCompilationResult.getChangedModules());
      }
    }
);
  }
}
