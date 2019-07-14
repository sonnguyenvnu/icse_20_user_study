public void rebuildModePopup(){
  modePopup=new JMenu();
  ButtonGroup modeGroup=new ButtonGroup();
  for (  final Mode m : base.getModeList()) {
    JRadioButtonMenuItem item=new JRadioButtonMenuItem(m.getTitle());
    item.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        if (!sketch.isModified()) {
          if (!base.changeMode(m)) {
            reselectMode();
            Messages.showWarning(Language.text("warn.cannot_change_mode.title"),Language.interpolate("warn.cannot_change_mode.body",m));
          }
        }
 else {
          reselectMode();
          Messages.showWarning("Save","Please save the sketch before changing the mode.");
        }
      }
    }
);
    modePopup.add(item);
    modeGroup.add(item);
    if (mode == m) {
      item.setSelected(true);
    }
  }
  modePopup.addSeparator();
  JMenuItem addLib=new JMenuItem(Language.text("toolbar.add_mode"));
  addLib.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      ContributionManager.openModes();
    }
  }
);
  modePopup.add(addLib);
  Toolkit.setMenuMnemsInside(modePopup);
}
