@Override public void addModeButtons(Box box,JLabel label){
  EditorButton debugButton=new EditorButton(this,"/lib/toolbar/debug",Language.text("toolbar.debug")){
    @Override public void actionPerformed(    ActionEvent e){
      jeditor.toggleDebug();
    }
  }
;
  if (((JavaEditor)editor).isDebuggerEnabled()) {
    debugButton.setSelected(true);
  }
  box.add(debugButton);
  addGap(box);
}
