@Override public List<EditorButton> createButtons(){
  final boolean debug=((JavaEditor)editor).isDebuggerEnabled();
  List<EditorButton> outgoing=new ArrayList<>();
  final String runText=debug ? Language.text("toolbar.debug") : Language.text("toolbar.run");
  runButton=new EditorButton(this,"/lib/toolbar/run",runText,Language.text("toolbar.present")){
    @Override public void actionPerformed(    ActionEvent e){
      handleRun(e.getModifiers());
    }
  }
;
  outgoing.add(runButton);
  if (debug) {
    stepButton=new EditorButton(this,"/lib/toolbar/step",Language.text("menu.debug.step"),Language.text("menu.debug.step_into"),Language.text("menu.debug.step_out")){
      @Override public void actionPerformed(      ActionEvent e){
        final int mask=ActionEvent.SHIFT_MASK | ActionEvent.ALT_MASK;
        jeditor.handleStep(e.getModifiers() & mask);
      }
    }
;
    outgoing.add(stepButton);
    continueButton=new EditorButton(this,"/lib/toolbar/continue",Language.text("menu.debug.continue")){
      @Override public void actionPerformed(      ActionEvent e){
        jeditor.handleContinue();
      }
    }
;
    outgoing.add(continueButton);
  }
  stopButton=new EditorButton(this,"/lib/toolbar/stop",Language.text("toolbar.stop")){
    @Override public void actionPerformed(    ActionEvent e){
      handleStop();
    }
  }
;
  outgoing.add(stopButton);
  return outgoing;
}
