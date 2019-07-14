private void vmBreakPointEvent(BreakpointEvent be){
  currentThread=be.thread();
  updateVariableInspector(currentThread);
  final LineID newCurrentLine=locationToLineID(be.location());
  javax.swing.SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      editor.setCurrentLine(newCurrentLine);
      editor.deactivateStep();
      editor.deactivateContinue();
    }
  }
);
  if (requestedStep != null) {
    runtime.vm().eventRequestManager().deleteEventRequest(requestedStep);
    requestedStep=null;
  }
  resumeOtherThreads(currentThread);
  paused=true;
  editor.statusHalted();
}
