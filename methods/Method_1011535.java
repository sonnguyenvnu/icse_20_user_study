public void update(IBreakpoint breakpoint,boolean isSelected){
  Color bg;
  if (isSelected) {
    bg=UIManager.getColor("Table.selectionBackground");
  }
 else {
    bg=UIManager.getColor("Table.background");
  }
  this.setBackground(bg);
  myCheckBox.setSelected(breakpoint.isEnabled());
  myCheckBox.setBackground(bg);
  myLabel.setText(breakpoint.getPresentation());
  myLabel.setIcon(BreakpointIconRenderer.getIconFor(breakpoint));
}
