@Override protected void dispose(){
  if (mySupertypesViewComponent != null && mySupertypesViewComponent.getParent() != null) {
    mySupertypesViewComponent.getParent().remove(mySupertypesViewComponent);
  }
  getProject().getModelAccess().runWriteAction(() -> {
    if (!myWasRegistered) {
      myModel.removeRootNode(myType.getContainingRoot());
      myWasRegistered=true;
    }
    MyBaseNodeDialog.super.dispose();
  }
);
}
