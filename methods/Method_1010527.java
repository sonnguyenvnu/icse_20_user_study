protected void update(){
  getModelAccess().runWriteAction(() -> {
    loadModules();
    fireModulesLoaded();
  }
);
}
