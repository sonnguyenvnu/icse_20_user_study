private void notify(MPSPsiModel model,Consumer<PsiManagerImpl> func){
  PsiManager manager=model.getManager();
  if (manager == null || !(manager instanceof PsiManagerImpl))   return;
  myModificationTracker.incCounter();
  manager.dropResolveCaches();
  func.accept((PsiManagerImpl)manager);
}
