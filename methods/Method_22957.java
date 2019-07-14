public void rebuild(){
  removeAll();
  List<EditorButton> buttons=createButtons();
  box=Box.createHorizontalBox();
  box.add(Box.createHorizontalStrut(Editor.LEFT_GUTTER));
  rolloverLabel=new JLabel();
  rolloverLabel.setFont(mode.getFont("toolbar.rollover.font"));
  rolloverLabel.setForeground(mode.getColor("toolbar.rollover.color"));
  for (  EditorButton button : buttons) {
    box.add(button);
    box.add(Box.createHorizontalStrut(GAP));
  }
  box.add(rolloverLabel);
  box.add(Box.createHorizontalGlue());
  addModeButtons(box,rolloverLabel);
  ModeSelector ms=new ModeSelector();
  box.add(ms);
  box.add(Box.createHorizontalStrut(Editor.RIGHT_GUTTER));
  setLayout(new BorderLayout());
  add(box,BorderLayout.CENTER);
}
