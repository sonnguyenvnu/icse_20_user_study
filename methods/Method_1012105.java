public void rebuild(){
  myProject.getModelAccess().runReadAction(new Runnable(){
    @Override public void run(){
      mySelectedConceptContainer=null;
      myRoots=createHierarchyForest();
      relayout();
    }
  }
);
}
