public void select(@NotNull final SModelReference model){
  myProject.getModelAccess().runReadInEDT(() -> {
    SModel m=model.resolve(myProject.getRepository());
    if (m != null) {
      NavigationSupport.getInstance().selectInTree(myProject,m,myFocus);
    }
  }
);
}
