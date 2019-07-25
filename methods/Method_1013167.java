/** 
 * Forces the form refresh
 */
public void refresh(){
  IManagedForm mForm=this.getManagedForm();
  if (mForm != null) {
    IToolBarManager toolbarManager=mForm.getForm().getToolBarManager();
    boolean modelRunning=getModel().isRunning();
    String title=mForm.getForm().getText();
    int titleIndex=Math.max(title.indexOf(RUNNING_TITLE),title.indexOf(CRASHED_TITLE));
    if (titleIndex != -1) {
      title=title.substring(0,titleIndex);
    }
    if (modelRunning) {
      if (getModel().isStale()) {
        mForm.getForm().setText(title + CRASHED_TITLE);
      }
 else {
        mForm.getForm().setText(title + RUNNING_TITLE);
      }
    }
 else {
      if (titleIndex != -1) {
        mForm.getForm().setText(title);
      }
    }
    toolbarManager.markDirty();
    toolbarManager.update(true);
    if (headClientTBM != null) {
      headClientTBM.markDirty();
      headClientTBM.update(true);
    }
    setEnabled(!modelRunning);
    mForm.getForm().update();
  }
}
