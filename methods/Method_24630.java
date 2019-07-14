private void vmStepEvent(StepEvent se){
  currentThread=se.thread();
  updateVariableInspector(currentThread);
  final LineID newCurrentLine=locationToLineID(se.location());
  javax.swing.SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      editor.setCurrentLine(newCurrentLine);
      editor.deactivateStep();
      editor.deactivateContinue();
    }
  }
);
  EventRequestManager mgr=runtime.vm().eventRequestManager();
  mgr.deleteEventRequest(se.request());
  requestedStep=null;
  paused=true;
  editor.statusHalted();
  if (!locationIsVisible(se.location())) {
    javax.swing.SwingUtilities.invokeLater(new Runnable(){
      @Override public void run(){
        stepOutIntoViewOrContinue();
      }
    }
);
  }
}
