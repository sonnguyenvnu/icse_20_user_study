@Override protected void init(){
  getRepository().getModelAccess().runWriteAction(new Runnable(){
    @Override public void run(){
      for (      Library l : getLibTable().getLibraries()) {
        addModuleForLibrary(l);
      }
    }
  }
);
  getLibTable().addListener(myListener);
}
