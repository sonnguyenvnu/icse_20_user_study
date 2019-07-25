public void terminate(){
  if (!ToolboxHandle.getInstanceStore().getBoolean(ToolboxHandle.I_RESTORE_LAST_SPEC)) {
    UIHelper.getActivePage().closeAllEditors(true);
    UIHelper.switchPerspective(ToolboxIntroPart.PERSPECTIVE_ID);
  }
}
