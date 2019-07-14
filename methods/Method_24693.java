/** 
 * Creates the debug menu. Includes ActionListeners for the menu items. Intended for adding to the menu bar.
 * @return The debug menu
 */
protected JMenu buildDebugMenu(){
  debugMenu=new JMenu(Language.text("menu.debug"));
  JMenuItem item;
  debugItem=Toolkit.newJMenuItem(Language.text("menu.debug.enable"),'D');
  debugItem.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      toggleDebug();
    }
  }
);
  debugMenu.add(debugItem);
  debugMenu.addSeparator();
  item=Toolkit.newJMenuItem(Language.text("menu.debug.continue"),KeyEvent.VK_U);
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleContinue();
    }
  }
);
  debugMenu.add(item);
  item.setEnabled(false);
  item=Toolkit.newJMenuItemExt("menu.debug.step");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleStep(0);
    }
  }
);
  debugMenu.add(item);
  item.setEnabled(false);
  item=Toolkit.newJMenuItemExt("menu.debug.step_into");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleStep(ActionEvent.SHIFT_MASK);
    }
  }
);
  debugMenu.add(item);
  item.setEnabled(false);
  item=Toolkit.newJMenuItemExt("menu.debug.step_out");
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleStep(ActionEvent.ALT_MASK);
    }
  }
);
  debugMenu.add(item);
  item.setEnabled(false);
  debugMenu.addSeparator();
  item=Toolkit.newJMenuItem(Language.text("menu.debug.toggle_breakpoint"),'B');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Messages.log("Invoked 'Toggle Breakpoint' menu item");
      toggleBreakpoint(getCurrentLineID().lineIdx());
    }
  }
);
  debugMenu.add(item);
  item.setEnabled(false);
  return debugMenu;
}
