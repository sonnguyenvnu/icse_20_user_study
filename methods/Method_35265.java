protected void setTitle(){
  Controller parentController=getParentController();
  while (parentController != null) {
    if (parentController instanceof BaseController && ((BaseController)parentController).getTitle() != null) {
      return;
    }
    parentController=parentController.getParentController();
  }
  String title=getTitle();
  ActionBar actionBar=getActionBar();
  if (title != null && actionBar != null) {
    actionBar.setTitle(title);
  }
}
