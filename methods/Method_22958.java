public List<EditorButton> createButtons(){
  runButton=new EditorButton(this,"/lib/toolbar/run",Language.text("toolbar.run"),Language.text("toolbar.present")){
    @Override public void actionPerformed(    ActionEvent e){
      handleRun(e.getModifiers());
    }
  }
;
  stopButton=new EditorButton(this,"/lib/toolbar/stop",Language.text("toolbar.stop")){
    @Override public void actionPerformed(    ActionEvent e){
      handleStop();
    }
  }
;
  return new ArrayList<>(Arrays.asList(runButton,stopButton));
}
