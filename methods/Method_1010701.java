public void select(@NotNull final SModuleReference module){
  myProject.getModelAccess().runReadInEDT(() -> {
    SModule m=module.resolve(myProject.getRepository());
    if (m != null) {
      NavigationSupport.getInstance().selectInTree(myProject,m,myFocus);
    }
  }
);
}
