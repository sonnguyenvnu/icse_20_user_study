void makeAndShowTab(boolean error,boolean loading){
  Editor editor=base.getActiveEditor();
  librariesTab.showFrame(editor,error,loading);
  modesTab.showFrame(editor,error,loading);
  toolsTab.showFrame(editor,error,loading);
  examplesTab.showFrame(editor,error,loading);
  updatesTab.showFrame(editor,error,loading);
}
